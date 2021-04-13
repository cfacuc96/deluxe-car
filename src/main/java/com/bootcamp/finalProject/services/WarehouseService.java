package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.PartDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.mnemonics.ExceptionMessage;
import com.bootcamp.finalProject.model.*;
import com.bootcamp.finalProject.repositories.ISubsidiaryRepository;
import com.bootcamp.finalProject.repositories.ISubsidiaryStockRepository;
import com.bootcamp.finalProject.repositories.OrderRepository;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.OrderNumberCMUtil;
import com.bootcamp.finalProject.utils.OrderResponseMapper;
import com.bootcamp.finalProject.utils.SubsidiaryResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.bootcamp.finalProject.utils.ValidationPartUtils.DSOrderTypeValidation;
import static com.bootcamp.finalProject.utils.ValidationPartUtils.deliveryStatusValidation;

@Service
public class WarehouseService implements IWarehouseService {

    SubsidiaryResponseMapper subsidiaryMapper = new SubsidiaryResponseMapper();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ISubsidiaryRepository subsidiaryRepository;

    @Autowired
    private ISubsidiaryStockRepository subsidiaryStockRepository;

    @Override
    public SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO orderRequest) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException {
        List<Order> orders;
        Subsidiary subsidiary;
        if (deliveryStatusValidation(orderRequest.getDeliveryStatus())) {
            Sort sort = DSOrderTypeValidation(orderRequest.getOrder());
            Long idSubsidiary = orderRequest.getDealerNumber();
            subsidiary = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
                if (orderRequest.getDeliveryStatus() == null) {
                    orders = orderRepository.findByIdSubsidiary(idSubsidiary, sort);
                } else {
                    orders = orderRepository.findByIdSubsidiaryAndDeliveryStatus(idSubsidiary, orderRequest.getDeliveryStatus(), sort);
                }
                subsidiary.setOrders(orders);
        } else {
            throw new DeliveryStatusException();
        }
        return subsidiaryMapper.toDTO(subsidiary);
    }

    @Override
    public OrderDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException, SubsidiaryNotFoundException {
        Long id = Long.valueOf(OrderNumberCMUtil.getNumberOR(orderNumberCM));

        Long idSubsidiary = Long.valueOf(OrderNumberCMUtil.getNumberCE(orderNumberCM));

        Subsidiary s = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
        Order o = orderRepository.findByIdOrderAndSubsidiary(id, s).orElseThrow(OrderIdNotFoundException::new);
        return new OrderResponseMapper().toDTO(o, s.getIdSubsidiary());
    }

    @Override
    public void changeDeliveryStatus(String orderNumberCM, String newStatus) throws InternalExceptionHandler{
        Long orderId = Long.valueOf(OrderNumberCMUtil.getNumberOR(orderNumberCM));

        Long idSubsidiary = Long.valueOf(OrderNumberCMUtil.getNumberCE(orderNumberCM));
        Subsidiary subsidiary = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
        Order order = orderRepository.findByIdOrderAndSubsidiary(orderId, subsidiary).orElseThrow(OrderIdNotFoundException::new);

        if ((order.getDeliveryStatus().equals(DeliveryStatus.PENDING)
                || order.getDeliveryStatus().equals(DeliveryStatus.DELAYED))){

            if (newStatus.equals(DeliveryStatus.CANCELED)){
                cancelDeliveryStatus(order);
            }else if (newStatus.equals(DeliveryStatus.FINISHED)){
                finishDeliveryStatus(order, subsidiary);
            }
            orderRepository.save(order);
        }else {
            //excepcion de que la orden habia sido cancelada o finalizada con anterioridad o
            throw new OrderDeliveryStatusIsconcludedException(order.getDeliveryStatus());
        }
    }

    public void cancelDeliveryStatus(Order order){
        List<OrderDetail> orderDetail = order.getOrderDetails();

        order.setDeliveryStatus("C");
        orderRepository.save(order);
    }

    public void finishDeliveryStatus(Order order, Subsidiary subsidiary){
        List<OrderDetail> orderDetails = order.getOrderDetails();
        SubsidiaryStock temp;
        for(OrderDetail o :orderDetails){
            temp = subsidiaryStockRepository.findByIdPart(o.getPartOrder().getIdPart(),subsidiary.getIdSubsidiary());
            if(temp==null){
                SubsidiaryStock subsidiaryStock = new SubsidiaryStock();
                subsidiaryStock.setQuantity(0);
                subsidiaryStock.setPart(o.getPartOrder());
                subsidiaryStock.setSubsidiary(subsidiary);
                subsidiaryStockRepository.save(subsidiaryStock);
                temp = subsidiaryStock;
            }
            temp.setQuantity(temp.getQuantity()+o.getQuantity());
            subsidiaryStockRepository.save(temp);
        }
        Date now = new Date();
        order.setDeliveryStatus(DeliveryStatus.FINISHED);
        order.setDeliveredDate(now);
    }
}