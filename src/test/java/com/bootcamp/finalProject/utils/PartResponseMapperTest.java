package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import com.bootcamp.finalProject.model.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class PartResponseMapperTest {

    PartResponseMapper mapper = new PartResponseMapper();
    SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void correctConvert(){
        //arrange
        List<PartResponseDTO> expected = new ArrayList<>();

        PartResponseDTO expectedIn = new PartResponseDTO();
        expectedIn.setPartCode(82);
        expectedIn.setDescription("parte1");
        expectedIn.setMaker("name");
        expectedIn.setQuantity(32);
        expectedIn.setDiscountType("nn33");
        expectedIn.setNormalPrice(99.99);
        expectedIn.setUrgentPrice(200.99);
        expectedIn.setNetWeight(22);
        expectedIn.setWidthDimension(2);
        expectedIn.setLongDimension(21);
        expectedIn.setTallDimension(32);
        expectedIn.setLastModification(datePattern.format(new Date()));
        expected.add(expectedIn);

        //act
        List<PartResponseDTO> actual = mapper.toDTO(getParts());

        //assert
        Assertions.assertIterableEquals(expected,actual);
    }

    public List<Part> getParts(){
        List<Part> partList = new ArrayList<>();
        Part part1 = new Part(99L,82,"parte1",2,32,21,22,32,new Date(),null,null,null, null);

        PartRecord partRecord1 = new PartRecord(1L,null,99.99,null,200.99,null,null);
        Provider provider = new Provider();
        provider.setName("name");

        DiscountRate discountRate = new DiscountRate();
        discountRate.setDiscount("nn33");

        part1.setProvider(provider);
        partRecord1.setDiscountRate(discountRate);
        List<PartRecord> partRecordList = new ArrayList<>();
        partRecordList.add(partRecord1);
        part1.setPartRecords(partRecordList);

        partList.add(part1);

        return partList;
    }
}
