package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.model.DiscountRate;
import org.modelmapper.ModelMapper;

public class DiscountRateMapper {

    public static DiscountRateDTO toDTO(DiscountRate discountRate){
        return new ModelMapper().map(discountRate, DiscountRateDTO.class);
    }

}
