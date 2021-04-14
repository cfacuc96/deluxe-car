package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderResponseDTO;
import com.bootcamp.finalProject.model.Order;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;
import static com.bootcamp.finalProject.utils.MapperUtils.getDifferencesInDays;

public class OrderResponseMapper
{
    OrderDetailResponseMapper mapper = new OrderDetailResponseMapper();
    SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
    
    public OrderDTO toDTO(Order order, Long idSubsidiary)
    {
        OrderDTO ret = new OrderDTO();

        ret.setOrderNumberCM(completeNumberByLength(String.valueOf(idSubsidiary),4) + "-" + completeNumberByLength(String.valueOf(order.getIdOrder()),8));
        ret.setOrderDate(datePattern.format(order.getOrderDate()));
        ret.setDeliveryDate(datePattern.format(order.getDeliveryDate()));
        ret.setDaysDelayed(getDifferencesInDays(order.getDeliveryDate(),order.getDeliveredDate()));
        ret.setDeliveryStatus(order.getDeliveryStatus());
        ret.setOrderDetails(order.getOrderDetails().stream().map(f -> mapper.toDTO(f)).collect(Collectors.toList()));

        return ret;
    }

    public OrderResponseDTO toOrderNumberCMDTO(Order order, Long idSubsidiary)
    {
        OrderResponseDTO a = new OrderResponseDTO();

        a.setOrderNumberCM(completeNumberByLength(String.valueOf(idSubsidiary),4) + "-" + completeNumberByLength(String.valueOf(order.getIdOrder()),8));
        a.setOrderDate(datePattern.format(order.getOrderDate()));
        a.setDeliveryStatus(order.getDeliveryStatus());
        a.setOrderDetails(order.getOrderDetails().stream().map(f -> mapper.toDTO(f)).collect(Collectors.toList()));
        return a;
    }
}
