package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IWarehouseService {
    SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO requestDTO) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException;

    OrderResponseDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException, SubsidiaryNotFoundException;

    void changeDeliveryStatus(String orderNumberCM, String newStatus) throws InternalExceptionHandler;

    SubsidiaryStockResponseDTO findSubsidiaryStock(SubsidiaryStockRequestDTO subsidiaryStockRequestDTO) throws SubsidiaryNotFoundException;

    OrderDTO newOrder(OrderDTO order, UserDetails user) throws PartAlreadyExistException, InvalidAccountTypeExtensionException, NotEnoughStock, PartNotExistException;

    BackOrderDTO newBackOrder(BackOrderDTO backOrder, UserDetails user) throws PartNotExistException, ThereIsAlredyStockException, InvalidAccountTypeExtensionException, InvalidBackOrderPriorityException;

    List<BackOrderDTO> finishBackOrder(Integer partCode) throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException;
}
