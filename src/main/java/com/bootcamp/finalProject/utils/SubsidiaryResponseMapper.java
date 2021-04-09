package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.model.Subsidiary;

import java.util.stream.Collectors;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;

public class SubsidiaryResponseMapper
{
    OrderResponseMapper mapper = new OrderResponseMapper();

    public SubsidiaryResponseDTO toDTO(Subsidiary subsidiary)
    {
        SubsidiaryResponseDTO ret = new SubsidiaryResponseDTO();
        String idSubsidiaryString = String.valueOf(subsidiary.getIdSubsidiary());
        ret.setDealerNumber(completeNumberByLength(idSubsidiaryString,4));

        var orders = subsidiary.getOrders().stream().map(f -> mapper.toDTO(f)).collect(Collectors.toList());

        ret.setOrders(orders);

        return ret;
    }
}
