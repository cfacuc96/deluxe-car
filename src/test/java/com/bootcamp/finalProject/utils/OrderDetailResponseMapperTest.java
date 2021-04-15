package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.OrderDetailDTO;
import com.bootcamp.finalProject.model.OrderDetail;
import com.bootcamp.finalProject.model.Part;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderDetailResponseMapperTest {

    @Test
    void correctConvert(){
        OrderDetailDTO expectedIn = new OrderDetailDTO();

        expectedIn.setPartCode("99");
        expectedIn.setDescription("capot");
        expectedIn.setQuantity(100);
        expectedIn.setAccountType("bancaria");
        expectedIn.setReason("P");

        OrderDetailDTO actual = OrderDetailResponseMapper.toDTO(getOrderDetail());

        Assertions.assertEquals(expectedIn,actual);


    }

    public OrderDetail getOrderDetail(){

        OrderDetail orderDetail = new OrderDetail(99L,"bancaria",100,"P",null,null);

        Part partOrder = new Part();
        partOrder.setDescription("capot");
        partOrder.setPartCode(99);
        orderDetail.setPartOrder(partOrder);


        return orderDetail;
    }
}
