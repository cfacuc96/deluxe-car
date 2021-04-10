package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.OrderDetailDTO;
import com.bootcamp.finalProject.model.OrderDetail;

public class OrderDetailResponseMapper
{
    public OrderDetailDTO toDTO(OrderDetail orderDetail)
    {
        OrderDetailDTO ret = new OrderDetailDTO();

        ret.setPartCode(String.valueOf(orderDetail.getPartOrder().getPartCode()));
        ret.setDescription(String.valueOf(orderDetail.getPartOrder().getDescription()));
        ret.setAccountType(orderDetail.getAccountType());
        ret.setQuantity(orderDetail.getQuantity());
        ret.setReason(orderDetail.getReason());

        return ret;
    }
}
