package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class PartControllerTest {

    @InjectMocks
    PartController partController;

    @Mock
    IWarehouseService warehouseService;

    @Mock
    IPartService partService;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
    }

    @Test
    void shouldReturnExceptionSinceTheParamOfOrderingDoesNotExist() {
        Map<String, String> order = new HashMap<>();
        order.put("queryType", "V");
        order.put("order", "23");
        order.put("date", "2020-01-11");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(order));

    }

    @Test
    void shouldReturnExceptionSinceParamOfSearchDoesNotExist() throws Exception {
        Map<String, String> queryType = new HashMap<>();
        queryType.put("queryType", "er");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(queryType));
    }

    @Test
    void shouldReturnExceptionSinceParamOfDateIsInABadFormat() {
        Map<String, String> order = new HashMap<>();
        order.put("queryType", "V");
        order.put("order", "2");
        order.put("date", "11-12-2019");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(order));
    }

    @Test
    void shouldReturnExceptionSinceParamOfDateIsUsingSlashInsteadOfHyphen() {
        Map<String, String> order = new HashMap<>();
        order.put("queryType", "V");
        order.put("order", "2");
        order.put("date", "2020/02/01");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(order));
    }

    @Test
    void shouldReturnExceptionSinceParamOfDateIsInTheFuture() {
        Map<String, String> order = new HashMap<>();
        order.put("queryType", "V");
        order.put("order", "2");
        order.put("date", "2025-12-12");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(order));
    }

    @Test
    void shouldNotThrowErrorSinceParamsAreCorrect() {
        Map<String, String> order = new HashMap<>();
        order.put("queryType", "V");
        order.put("order", "2");
        order.put("date", "2020-12-12");
        Assertions.assertDoesNotThrow(() -> partController.findPart(order));
    }

    @Test
    void getOrderByOrderNumberCMShouldThrowException() {
        //Arrange
        String s = "000-478";
        ///Act and Assert
        Assertions.assertThrows(Exception.class, () -> {
            partController.findByOrderNumberCM(s);
        });

    }
}
