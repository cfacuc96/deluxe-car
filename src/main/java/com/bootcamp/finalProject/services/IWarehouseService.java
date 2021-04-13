package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderIdNotFoundException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;
import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.PartDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.Part;

public interface IWarehouseService {
    SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO requestDTO) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException;

    OrderDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException, SubsidiaryNotFoundException;

    SubsidiaryStockResponseDTO findSubsidiaryStock(SubsidiaryStockRequestDTO subsidiaryStockRequestDTO) throws SubsidiaryNotFoundException;

    void changeDeliveryStatus(String orderNumberCM, String newStatus) throws OrderIdNotFoundException, SubsidiaryNotFoundException, PartAlreadyExistException;
    OrderDTO newOrder(OrderDTO order) throws PartAlreadyExistException, InvalidAccountTypeExtensionException, NotEnoughStock, PartNotExistException;
}
