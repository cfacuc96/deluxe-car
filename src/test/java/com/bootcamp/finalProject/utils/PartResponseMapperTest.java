package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PartResponseMapperTest {

    PartResponseMapper mapper = new PartResponseMapper();


    @Test
    public void correctConvert(){
        //arrange
        List<PartResponseDTO> expected = new ArrayList<>();

        PartResponseDTO expectedIn = new PartResponseDTO();
        expectedIn.setPartCode(82);
        expectedIn.setDescription("parte1");
        expectedIn.setMaker("maker");
        //expectedIn.setQuantity(
        expectedIn.setNormalPrice(99.99);
        expectedIn.setUrgentPrice(200.99);
        expectedIn.setNetWeight(22);
        expectedIn.setWidthDimension(2);
        expectedIn.setLongDimension(21);
        expectedIn.setTallDimension(32);
        expectedIn.setLastModification(null);
        expected.add(expectedIn);

        //act
        List<PartResponseDTO> actual = mapper.toDTO(getParts());

        //assert

        Assertions.assertIterableEquals(expected,actual);
    }


    public List<Part> getParts(){
        List<Part> partList = new ArrayList<>();
        Part part1 = new Part(99L,82,"parte1",2,32,21,22,"maker",null,null);
        PartRecord partRecord1 = new PartRecord(1L,null,99.99,200.99,null);
        List<PartRecord> partRecordList = new ArrayList<>();
        partRecordList.add(partRecord1);
        part1.setPartRecords(partRecordList);

        partList.add(part1);

        return partList;
    }
}
