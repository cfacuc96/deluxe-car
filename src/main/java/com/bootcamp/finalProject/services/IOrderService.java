package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;

public interface IOrderService {
    SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO requestDTO) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException;
}
