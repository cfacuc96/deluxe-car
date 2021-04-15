package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.model.DiscountRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DiscountRateMapperTest {

    @Test
    void correctConvert(){
        DiscountRateDTO expectedIn = new DiscountRateDTO();

        expectedIn.setIdDiscountRate(99L);
        expectedIn.setDiscount("%30");
        expectedIn.setDescription("descuento");

        DiscountRateDTO actual = DiscountRateMapper.toDTO(getDiscountRate());

        Assertions.assertEquals(expectedIn,actual);


    }

    public DiscountRate getDiscountRate(){
        DiscountRate discountRate = new DiscountRate(99L,"descuento","%30",null);

        return discountRate;
    }
}
