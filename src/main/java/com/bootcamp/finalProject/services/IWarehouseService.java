package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderIdNotFoundException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;

public interface IWarehouseService {
    SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO requestDTO) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException;

    OrderDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException, SubsidiaryNotFoundException;

    SubsidiaryStockResponseDTO findSubsidiaryStock(SubsidiaryStockRequestDTO subsidiaryStockRequestDTO) throws SubsidiaryNotFoundException;

    void changeDeliveryStatus(String orderNumberCM, String newStatus) throws OrderIdNotFoundException, SubsidiaryNotFoundException;
}
