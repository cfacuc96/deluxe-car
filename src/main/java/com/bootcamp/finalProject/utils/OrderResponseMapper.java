package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.model.Order;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;
import static com.bootcamp.finalProject.utils.MapperUtils.getDifferencesInDays;

public class OrderResponseMapper
{
    OrderDetailResponseMapper mapper = new OrderDetailResponseMapper();
    SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
    
    public OrderDTO toDTO(Order order)
    {
        OrderDTO ret = new OrderDTO();

        ret.setOrderNumber(completeNumberByLength(String.valueOf(order.getIdOrder()),8));
        ret.setOrderDate(datePattern.format(order.getOrderDate()));
        ret.setDeliveryDate(datePattern.format(order.getDeliveryDate()));
        ret.setDaysDelayed(getDifferencesInDays(order.getDeliveryDate(),order.getDeliveredDate()));
        ret.setDeliveryStatus(order.getDeliveryStatus());
        ret.setOrderDetails(order.getOrderDetails().stream().map(f -> mapper.toDTO(f)).collect(Collectors.toList()));

        return ret;
    }
}
