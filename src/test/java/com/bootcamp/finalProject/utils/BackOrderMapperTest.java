package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.BackOrderDTO;
import com.bootcamp.finalProject.dtos.BackOrderDetailDTO;
import com.bootcamp.finalProject.model.BackOrder;
import com.bootcamp.finalProject.model.BackOrderDetail;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.Subsidiary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackOrderMapperTest {

    @Test
    public void correctConvert(){
        //arrange
        BackOrder backOrder = new BackOrder();
        backOrder.setBackOrderDate(parseDate("2021-04-01"));
        backOrder.setBackOrderPriority("H");
        backOrder.setBackOrderStatus("F");
        backOrder.setFinishBackOrderDate(parseDate("2021-04-20"));
        backOrder.setIdBackOrder(1L);

        BackOrderDetail backOrderDetail = new BackOrderDetail();
        backOrderDetail.setIdOrderDetail(1L);
        backOrderDetail.setQuantity(1);
        backOrderDetail.setAccountType("R");

        Part part = new Part();
        part.setPartCode(11111112);
        part.setDescription("TEST");
        backOrderDetail.setPartBackOrder(part);
        backOrderDetail.setBackOrder(backOrder);

        backOrder.setBackOrderDetail(backOrderDetail);
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setIdSubsidiary(1L);
        backOrder.setSubsidiary(subsidiary);

        String orderNumberCM = "0001-00000007";
        BackOrderDTO expected = new BackOrderDTO();
        expected.setBackOrderNumberCM("0001-00000001");
        expected.setOrderNumberCM("0001-00000007");
        expected.setBackOrderDate("2021-04-01");
        expected.setFinishBackOrderDate("2021-04-20");
        expected.setBackOrderPriority("H");
        expected.setBackOrderStatus("F");
        BackOrderDetailDTO backOrderDetailDTO = new BackOrderDetailDTO();
        backOrderDetailDTO.setPartCode(11111112);
        backOrderDetailDTO.setDescription("TEST");
        backOrderDetailDTO.setAccountType("R");
        backOrderDetailDTO.setQuantity(1);
        expected.setBackOrderDetail(backOrderDetailDTO);

        //act
        BackOrderDTO actual = BackOrderMapper.toDTO(backOrder, orderNumberCM);

        Assertions.assertEquals(expected, actual);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
