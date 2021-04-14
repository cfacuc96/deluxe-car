package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class BasicAuxCrudControllerTest {

    @InjectMocks
    BasicAuxCrudController basicAuxCrudController;

    @Mock
    IWarehouseService warehouseService;

    @Mock
    IPartService partService;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
    }

    @Test
    void idIsNotNullForAddDiscountRateDTO(){
        //Arrange
        DiscountRateDTO dto = new DiscountRateDTO();
        dto.setIdDiscountRate(1L);
        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, ()->basicAuxCrudController.addDiscountRate(dto));
    }

    @Test
    void descriptionOrDiscountAreNullForAddDiscountRateDTO(){
        //Arrange
        DiscountRateDTO dto = new DiscountRateDTO();
        dto.setIdDiscountRate(null);
        dto.setDescription("");
        dto.setDiscount("");
        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, ()->basicAuxCrudController.addDiscountRate(dto));
    }

    @Test
    void successfullyAddDiscountRateDTO(){
        //Arrange
        DiscountRateDTO dto = new DiscountRateDTO();
        dto.setIdDiscountRate(null);
        dto.setDescription("Part one");
        dto.setDiscount("%30");
        //Act and Assert
        Assertions.assertDoesNotThrow(()->basicAuxCrudController.addDiscountRate(dto));
    }



}

