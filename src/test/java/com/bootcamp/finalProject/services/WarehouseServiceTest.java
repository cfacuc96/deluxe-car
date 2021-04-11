package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;
import com.bootcamp.finalProject.repositories.ISubsidiaryRepository;
import com.bootcamp.finalProject.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class WarehouseServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    ISubsidiaryRepository subsidiaryRepository;

    IWarehouseService warehouseService;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
        this.warehouseService = new WarehouseService(orderRepository, subsidiaryRepository);
    }

    @Test
    public void findSubsidiaryOrdersThrowsSubsidiaryNotFoundException() {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setDeliveryStatus("P");
        requestDTO.setDealerNumber(0L);
        
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.empty());

        Assertions.assertThrows(SubsidiaryNotFoundException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }

    @Test
    public void findSubsidiaryOrdersThrowsSubsidiaryNotFoundExceptionDealerNumberNull() {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setDeliveryStatus("P");
        requestDTO.setDealerNumber(null);

        Assertions.assertThrows(SubsidiaryNotFoundException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }

    @Test
    public void findSubsidiaryOrdersThrowsDeliveryStatusExceptionDiffValue() {
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setDeliveryStatus("A");
        requestDTO.setDealerNumber(null);

        Assertions.assertThrows(DeliveryStatusException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }
}
