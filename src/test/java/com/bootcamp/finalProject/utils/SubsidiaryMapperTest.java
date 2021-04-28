package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubsidiaryMapperTest {

    SubsidiaryResponseMapper mapper = new SubsidiaryResponseMapper();
    SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void subsidiaryOrdersWithStatusCanceled() {
        Subsidiary subsidiary = new Subsidiary();
        List<Order> orders = new ArrayList<>();
        subsidiary.setIdSubsidiary(1L);
        Order o = new Order();
        o.setIdOrder(1L);
        o.setOrderDate(new Date());
        o.setDeliveryDate(new Date());
        o.setDeliveryStatus(DeliveryStatus.CANCELED);
        o.setOrderDetails(new ArrayList<>());
        OrderDetail od = new OrderDetail();
        Part part = new Part();
        part.setPartCode(11223344);
        part.setDescription("Part description");
        od.setPartOrder(part);
        od.setAccountType("R");
        od.setQuantity(10);
        od.setReason("");
        o.getOrderDetails().add(od);

        orders.add(o);
        subsidiary.setOrders(orders);

        OrderDetailDTO odDTO = new OrderDetailDTO();
        odDTO.setPartCode("11223344");
        odDTO.setDescription("Part description");
        odDTO.setAccountType("R");
        odDTO.setQuantity(10);
        odDTO.setReason("");

        OrderDTO oDTO = new OrderDTO();
        oDTO.setOrderNumberCM("0001-00000001");
        oDTO.setOrderDate(datePattern.format(o.getOrderDate()));
        oDTO.setDeliveryDate(datePattern.format(o.getDeliveryDate()));
        oDTO.setDaysDelayed(0);
        oDTO.setDeliveryStatus(DeliveryStatus.CANCELED);
        oDTO.setOrderDetails(new ArrayList<>());
        oDTO.getOrderDetails().add(odDTO);

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("0001");
        expected.setOrders(new ArrayList<>());
        expected.getOrders().add(oDTO);

        Assertions.assertEquals(expected, mapper.toOrderDTO(subsidiary));
    }

    @Test
    public void subsidiaryOrdersWithStatusFinished() {
        Subsidiary subsidiary = new Subsidiary();
        List<Order> orders = new ArrayList<>();
        subsidiary.setIdSubsidiary(1L);
        Order o = new Order();
        o.setIdOrder(1L);
        o.setOrderDate(new Date());
        o.setDeliveryDate(new Date());
        o.setDeliveredDate(new Date());
        o.setDeliveryStatus(DeliveryStatus.FINISHED);
        o.setOrderDetails(new ArrayList<>());
        OrderDetail od = new OrderDetail();
        Part part = new Part();
        part.setPartCode(11223344);
        part.setDescription("Part description");
        od.setPartOrder(part);
        od.setAccountType("R");
        od.setQuantity(10);
        od.setReason("");
        o.getOrderDetails().add(od);

        orders.add(o);
        subsidiary.setOrders(orders);

        OrderDetailDTO odDTO = new OrderDetailDTO();
        odDTO.setPartCode("11223344");
        odDTO.setDescription("Part description");
        odDTO.setAccountType("R");
        odDTO.setQuantity(10);
        odDTO.setReason("");

        OrderDTO oDTO = new OrderDTO();
        oDTO.setOrderNumberCM("0001-00000001");
        oDTO.setOrderDate(datePattern.format(o.getOrderDate()));
        oDTO.setDeliveryDate(datePattern.format(o.getDeliveryDate()));
        oDTO.setDaysDelayed(0);
        oDTO.setDeliveryStatus(DeliveryStatus.FINISHED);
        oDTO.setOrderDetails(new ArrayList<>());
        oDTO.getOrderDetails().add(odDTO);

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("0001");
        expected.setOrders(new ArrayList<>());
        expected.getOrders().add(oDTO);

        Assertions.assertEquals(expected, mapper.toOrderDTO(subsidiary));
    }

    @Test
    public void subsidiaryStock() {
        Subsidiary subsidiary = new Subsidiary();
        List<SubsidiaryStock> stocks = new ArrayList<>();
        subsidiary.setIdSubsidiary(1L);
        subsidiary.setName("subName");
        SubsidiaryStock s = new SubsidiaryStock();

        s.setSubsidiary(subsidiary);
        s.setQuantity(10);
        Part part = new Part();
        part.setPartCode(11223344);
        part.setDescription("Part description");
        s.setPart(part);

        stocks.add(s);
        subsidiary.setSubsidiaryStocks(stocks);


        SubsidiaryStockResponseDTO expected = new SubsidiaryStockResponseDTO();
        expected.setDealerNumber("0001");
        expected.setName(subsidiary.getName());
        expected.setSubsidiaryStocks(new ArrayList<>());

        SubsidiaryStockDTO sDTO = new SubsidiaryStockDTO();
        sDTO.setPartCode("11223344");
        sDTO.setQuantityInSubsidiary("10");
        sDTO.setDescription("Part description");

        expected.getSubsidiaryStocks().add(sDTO);

        Assertions.assertEquals(expected, mapper.toStockDTO(subsidiary));
    }
}
