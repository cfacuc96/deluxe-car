package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.OrderDetail;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.repositories.ISubsidiaryRepository;
import com.bootcamp.finalProject.repositories.OrderRepository;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.OrderNumberCMUtil;
import com.bootcamp.finalProject.utils.OrderResponseMapper;
import com.bootcamp.finalProject.utils.SubsidiaryResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    private PartRepository partRepository;

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
        return subsidiaryMapper.toOrderDTO(subsidiary);
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
    public SubsidiaryStockResponseDTO findSubsidiaryStock(SubsidiaryStockRequestDTO subsidiaryStockRequestDTO) throws SubsidiaryNotFoundException {

        Subsidiary subsidiary = subsidiaryRepository.findById(subsidiaryStockRequestDTO.getDealerNumber()).orElseThrow(SubsidiaryNotFoundException::new);

        return new SubsidiaryResponseMapper().toStockDTO(subsidiary);
    }
    
    public void changeDeliveryStatus(String orderNumberCM, String newStatus) throws SubsidiaryNotFoundException, OrderIdNotFoundException {
        Long orderId = Long.valueOf(OrderNumberCMUtil.getNumberOR(orderNumberCM));

        Long idSubsidiary = Long.valueOf(OrderNumberCMUtil.getNumberCE(orderNumberCM));
        Subsidiary subsidiary = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
        Order order = orderRepository.findByIdOrderAndSubsidiary(orderId, subsidiary).orElseThrow(OrderIdNotFoundException::new);

        //"0001-00000001"
        if (newStatus.equals(DeliveryStatus.CANCELED)){
            cancelDeliveryStatus(order);
        }else if (newStatus.equals(DeliveryStatus.FINISHED)){
            //change to finished
        }
        orderRepository.save(order);
    }

    public void cancelDeliveryStatus(Order order){
        List<OrderDetail> orderDetail = order.getOrderDetails();

        orderDetail.forEach(partToUpdate -> {
            Part part = partRepository.findById(partToUpdate.getPartOrder().getIdPart()).orElseThrow();
            part.setQuantity(part.getQuantity()+partToUpdate.getQuantity());
            partRepository.save(part);
        });
        order.setDeliveryStatus(DeliveryStatus.CANCELED);
        orderRepository.save(order);
    }
}