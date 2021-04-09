package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderIdNotFoundException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;

public interface IWarehouseService {
    SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO requestDTO) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException;

    OrderDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException;
}
