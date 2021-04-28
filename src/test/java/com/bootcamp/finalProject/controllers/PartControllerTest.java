package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.dtos.PartPriceDTO;
import com.bootcamp.finalProject.dtos.PartRecordDTO;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


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
    void findPartShouldReturnExceptionSinceAllParamsAreNull() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldReturnExceptionSinceDateNotNullAndQueryTypeNull() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("date", "2021-04-11");

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldReturnExceptionSinceOrderIsNotANumber() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "P");
        requestParams.put("date", "2021-04-11");
        requestParams.put("order", "a");

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldNotThrowErrorSinceOrderIsNull() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "V");
        requestParams.put("date", "2020-12-12");

        //Act and Assert

        when(partService.findPart(null)).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldNotThrowErrorSinceOrderIsEmptyString() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "V");
        requestParams.put("order", "");
        requestParams.put("date", "2020-12-12");

        //Act and Assert

        when(partService.findPart(null)).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldReturnExceptionSinceParamOfDateIsInABadFormat() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "V");
        requestParams.put("order", "2");
        requestParams.put("date", "11-12-2019");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldReturnExceptionSinceParamOfDateIsUsingSlashInsteadOfHyphen() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "V");
        requestParams.put("order", "2");
        requestParams.put("date", "2020/02/01");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldReturnExceptionSinceParamOfDateIsInTheFuture() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "V");
        requestParams.put("order", "2");
        requestParams.put("date", "2025-12-12");
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findPart(requestParams));
    }

    @Test
    void findPartShouldNotThrowErrorSinceParamsAreCorrect() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("queryType", "V");
        requestParams.put("order", "2");
        requestParams.put("date", "2020-12-12");

        //Act and Assert

        when(partService.findPart(null)).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> partController.findPart(requestParams));
    }

    @Test
    void findOrdersShouldReturnExceptionSinceAllParamsAreNull() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldReturnExceptionSinceDealerNumberNull() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("deliveryStatus", "P");

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldReturnExceptionSinceDeliveryStatusNotValid() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("dealerNumber", "0001");
        requestParams.put("deliveryStatus", "A");

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldNotThrowExceptionWithNullDeliveryStatus() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("dealerNumber", "0001");

        //Act
        when(warehouseService.findSubsidiaryOrders(null)).thenReturn(null);

        //Assert
        Assertions.assertDoesNotThrow(() -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldReturnExceptionSinceOrderIsNotANumber() {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("dealerNumber", "0001");

        requestParams.put("order", "a");

        //Act and Assert
        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldNotThrowErrorWithNullOrder() throws OrderTypeException, TypeOfQueryException, DeliveryStatusException, SubsidiaryNotFoundException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("dealerNumber", "0001");
        requestParams.put("deliveryStatus", "P");

        //Act
        when(warehouseService.findSubsidiaryOrders(null)).thenReturn(null);

        //Assert
        Assertions.assertDoesNotThrow(() -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldNotThrowErrorWithOrderEmptyString() throws OrderTypeException, TypeOfQueryException, DeliveryStatusException, SubsidiaryNotFoundException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("dealerNumber", "0001");
        requestParams.put("deliveryStatus", "P");
        requestParams.put("order", "");

        //Act
        when(warehouseService.findSubsidiaryOrders(null)).thenReturn(null);

        //Assert
        Assertions.assertDoesNotThrow(() -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void findOrdersShouldNotThrowExceptionAllParamsCorrect() throws OrderTypeException, TypeOfQueryException, DeliveryStatusException, SubsidiaryNotFoundException {
        //Arrange
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("dealerNumber", "0001");
        requestParams.put("deliveryStatus", "P");
        requestParams.put("order", "1");

        //Act
        when(warehouseService.findSubsidiaryOrders(null)).thenReturn(null);

        //Assert
        Assertions.assertDoesNotThrow(() -> partController.findSubsidiaryOrders(requestParams));
    }

    @Test
    void getOrderByOrderNumberCMShouldThrowException() {
        //Arrange
        String s = "000-478";
        ///Act and Assert
        Assertions.assertThrows(Exception.class, () -> partController.findByOrderNumberCM(s));
    }

    @Test
    void getOrderByOrderNumberCMNotThrowException() throws OrderIdNotFoundException, SubsidiaryNotFoundException {
        //Arrange
        String s = "0001-00000002";
        ///Act and Assert
        when(warehouseService.findByOrderNumberCM(s)).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> partController.findByOrderNumberCM(s));
    }

    @Test
    public void getHistoricPrice() throws InternalExceptionHandler {
        //arrange
        PartPriceDTO expected = new PartPriceDTO();
        expected.setPartCode(1);
        expected.setDescription("Parte");
        expected.setNetWeight(1);
        expected.setLongDimension(1);
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setMaker("Mercedes");

        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        PartRecordDTO partRecordDTO = new PartRecordDTO();
        partRecordDTO.setCreatedAt("2020-04-01");
        partRecordDTO.setNormalPrice(1D);
        partRecordDTO.setUrgentPrice(1D);

        DiscountRateDTO discountRateDTO = new DiscountRateDTO();
        discountRateDTO.setIdDiscountRate(1L);
        discountRateDTO.setDiscount("%30");
        discountRateDTO.setDescription("Discount for part Espolon BMW 320i");

        partRecordDTO.setDiscountRate(discountRateDTO);
        partRecordDTOList.add(partRecordDTO);
        expected.setHistoricPrice(partRecordDTOList);

        Integer partCode = 1;
        String dateFrom = null;
        String dateTo = null;

        when(partService.historicPrice(partCode, null, null)).thenReturn(expected);

        PartPriceDTO actual = partController.historicPrice(partCode, dateFrom, dateTo);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getHistoricPriceDateFromBadRequest() throws InternalExceptionHandler {
        //arrange
        PartPriceDTO expected = new PartPriceDTO();
        expected.setPartCode(1);
        expected.setDescription("Parte");
        expected.setNetWeight(1);
        expected.setLongDimension(1);
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setMaker("Mercedes");

        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        PartRecordDTO partRecordDTO = new PartRecordDTO();
        partRecordDTO.setCreatedAt("2020-04-01");
        partRecordDTO.setNormalPrice(1D);
        partRecordDTO.setUrgentPrice(1D);

        DiscountRateDTO discountRateDTO = new DiscountRateDTO();
        discountRateDTO.setIdDiscountRate(1L);
        discountRateDTO.setDiscount("%30");
        discountRateDTO.setDescription("Discount for part Espolon BMW 320i");

        partRecordDTO.setDiscountRate(discountRateDTO);
        partRecordDTOList.add(partRecordDTO);
        expected.setHistoricPrice(partRecordDTOList);

        Integer partCode = 1;
        String dateFrom = "2020/04/01";
        String dateTo = null;

        when(partService.historicPrice(partCode, null, null)).thenReturn(expected);

        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.historicPrice(partCode, dateFrom, dateTo));
    }

    @Test
    public void getHistoricPriceDateToBadRequest() throws InternalExceptionHandler {
        //arrange
        PartPriceDTO expected = new PartPriceDTO();
        expected.setPartCode(1);
        expected.setDescription("Parte");
        expected.setNetWeight(1);
        expected.setLongDimension(1);
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setMaker("Mercedes");

        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        PartRecordDTO partRecordDTO = new PartRecordDTO();
        partRecordDTO.setCreatedAt("2020-04-01");
        partRecordDTO.setNormalPrice(1D);
        partRecordDTO.setUrgentPrice(1D);

        DiscountRateDTO discountRateDTO = new DiscountRateDTO();
        discountRateDTO.setIdDiscountRate(1L);
        discountRateDTO.setDiscount("%30");
        discountRateDTO.setDescription("Discount for part Espolon BMW 320i");

        partRecordDTO.setDiscountRate(discountRateDTO);
        partRecordDTOList.add(partRecordDTO);
        expected.setHistoricPrice(partRecordDTOList);

        Integer partCode = 1;
        String dateFrom = null;
        String dateTo = "2020/04/01";

        when(partService.historicPrice(partCode, null, null)).thenReturn(expected);

        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.historicPrice(partCode, dateFrom, dateTo));
    }

    @Test
    public void getHistoricPriceComplete() throws InternalExceptionHandler {
        //arrange
        PartPriceDTO expected = new PartPriceDTO();
        expected.setPartCode(1);
        expected.setDescription("Parte");
        expected.setNetWeight(1);
        expected.setLongDimension(1);
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setMaker("Mercedes");

        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        PartRecordDTO partRecordDTO = new PartRecordDTO();
        partRecordDTO.setCreatedAt("2020-04-01");
        partRecordDTO.setNormalPrice(1D);
        partRecordDTO.setUrgentPrice(1D);

        DiscountRateDTO discountRateDTO = new DiscountRateDTO();
        discountRateDTO.setIdDiscountRate(1L);
        discountRateDTO.setDiscount("%30");
        discountRateDTO.setDescription("Discount for part Espolon BMW 320i");

        partRecordDTO.setDiscountRate(discountRateDTO);
        partRecordDTOList.add(partRecordDTO);
        expected.setHistoricPrice(partRecordDTOList);

        Integer partCode = 1;
        String dateFrom = "2020-04-19";
        String dateTo = "2020-04-01";

        when(partService.historicPrice(partCode, parseDate(dateFrom), parseDate(dateTo))).thenReturn(expected);

        PartPriceDTO actual = partController.historicPrice(partCode, dateFrom, dateTo);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getHistoricPriceDateToFutureException() throws InternalExceptionHandler {
        //arrange
        PartPriceDTO expected = new PartPriceDTO();
        expected.setPartCode(1);
        expected.setDescription("Parte");
        expected.setNetWeight(1);
        expected.setLongDimension(1);
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setMaker("Mercedes");

        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        PartRecordDTO partRecordDTO = new PartRecordDTO();
        partRecordDTO.setCreatedAt("2020-04-01");
        partRecordDTO.setNormalPrice(1D);
        partRecordDTO.setUrgentPrice(1D);

        DiscountRateDTO discountRateDTO = new DiscountRateDTO();
        discountRateDTO.setIdDiscountRate(1L);
        discountRateDTO.setDiscount("%30");
        discountRateDTO.setDescription("Discount for part Espolon BMW 320i");

        partRecordDTO.setDiscountRate(discountRateDTO);
        partRecordDTOList.add(partRecordDTO);
        expected.setHistoricPrice(partRecordDTOList);

        Integer partCode = 1;
        String dateFrom = "2020-04-19";
        String dateTo = "2021-05-01";

        when(partService.historicPrice(partCode, null, null)).thenReturn(expected);

        Assertions.assertThrows(InternalExceptionHandler.class, () -> partController.historicPrice(partCode, dateFrom, dateTo));

    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}