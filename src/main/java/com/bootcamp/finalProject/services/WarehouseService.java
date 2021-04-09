package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;
import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.repositories.ISubsidiaryRepository;
import com.bootcamp.finalProject.repositories.OrderRepository;
import com.bootcamp.finalProject.utils.OrderResponseMapper;
import com.bootcamp.finalProject.utils.SubsidiaryResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO orderRequest) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException {
        List<Order> orders = new ArrayList<>();
        Subsidiary subsidiary = null;
        if (deliveryStatusValidation(orderRequest.getDeliveryStatus())) {
            Sort sort = DSOrderTypeValidation(orderRequest.getOrder());
            Long idSubsidiary = orderRequest.getDealerNumber();
            if (orderRequest.getDeliveryStatus() == null) {
                subsidiary = subsidiaryRepository.findByIdOrder(idSubsidiary);
//                List<Order> = orderRepository.findByOrderByIdSubsidiary(idSubsidiary);
            } else {
                subsidiary = subsidiaryRepository.findByDeliveryStatus(idSubsidiary, orderRequest.getDeliveryStatus());
            }
            if(subsidiary == null){
                throw new SubsidiaryNotFoundException();
            }
        }else{
            throw new DeliveryStatusException();
        }
        return subsidiaryMapper.toDTO(subsidiary);
    }
}
