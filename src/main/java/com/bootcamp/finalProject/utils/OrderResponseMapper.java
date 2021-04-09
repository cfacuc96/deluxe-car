package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderDetailDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.model.Subsidiary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class OrderResponseMapper
{
    SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
    
    public SubsidiaryResponseDTO toDTO(Subsidiary subsidiary)
    {
        SubsidiaryResponseDTO ret = new SubsidiaryResponseDTO();
        String idSubsidiaryString = String.valueOf(subsidiary.getIdSubsidiary());
        ret.setDealerNumber(completeNumberByLength(idSubsidiaryString,4));

        var orders = subsidiary.getOrders().stream().map( f ->
                new OrderDTO(completeNumberByLength(String.valueOf(f.getIdOrder()),8),
                        datePattern.format(f.getOrderDate()),
                        datePattern.format(f.getDeliveryDate()),
                        getDifferencesInDays(f.getDeliveryDate(),f.getDeliveredDate()),
                        f.getDeliveryStatus(),
                        f.getOrderDetails().stream().map(g ->
                new OrderDetailDTO(String.valueOf(g.getPartOrder().getPartCode()),
                        g.getPartOrder().getDescription(),
                        g.getQuantity()
                )).collect(Collectors.toList()))
                ).collect(Collectors.toList());
        ret.setOrders(orders);

        return ret;
    }

    //el numero ingresado debe ser String y el resultado es String
    //hay que tener en cuenta para validar que
    // si la long del numero es mayor al length ingresado devuelve "".
    private String completeNumberByLength(String Number, int length)
    {
        String result = "";
        if(Number.length() < length)
        {
            int limit = length - Number.length();
            result = replicate('0', limit);
            result += Number;
        }
        return result;
    }

    private String replicate(char character, int limit)
    {
        String result = "";
        for (int i = 0; i < limit ; i++)
        {
            result += character;
        }
        return result;
    }


    private Integer getDifferencesInDays(Date deliveryDate, Date deliveredDate)
    {
        Integer result = 0;
        if(deliveredDate != null){
            long diffInMillies = Math.abs(deliveryDate.getTime() - deliveredDate.getTime());
            result = Math.toIntExact((TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1));
        }
        return result;
    }
}
