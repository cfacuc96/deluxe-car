package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.*;

public interface IWarehouseService {
    SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO requestDTO) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException;

    OrderDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException, SubsidiaryNotFoundException;

    void changeDeliveryStatus(String orderNumberCM, String newStatus) throws InternalExceptionHandler;


}
