package com.bootcamp.finalProject.utils;

import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.dtos.PartPriceDTO;
import com.bootcamp.finalProject.dtos.PartRecordDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import com.bootcamp.finalProject.model.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
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

    @Test
    public void toPartPriceDTO(){
        //arrange
        PartPriceDTO expected = new PartPriceDTO();
        expected.setPartCode(21111114);
        expected.setDescription("TEST");
        expected.setNetWeight(1);
        expected.setLongDimension(1);
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setMaker("Jose");

        List<PartRecordDTO> partRecordDTOList = new ArrayList<>();
        PartRecordDTO partRecordDTO = new PartRecordDTO();
        partRecordDTO.setCreatedAt("2021-04-01");
        partRecordDTO.setNormalPrice(2D);
        partRecordDTO.setUrgentPrice(2D);

        DiscountRateDTO discountRateDTO = new DiscountRateDTO();
        discountRateDTO.setIdDiscountRate(1L);
        discountRateDTO.setDiscount("%20");
        discountRateDTO.setDescription("Clarin 365");

        partRecordDTO.setDiscountRate(discountRateDTO);
        partRecordDTOList.add(partRecordDTO);
        expected.setHistoricPrice(partRecordDTOList);

        Part partMocked = new Part();
        partMocked.setIdPart(1L);
        partMocked.setPartCode(21111114);
        partMocked.setDescription("TEST");
        partMocked.setWidthDimension(1);
        partMocked.setTallDimension(1);
        partMocked.setLongDimension(1);
        partMocked.setNetWeight(1);
        partMocked.setQuantity(1);

        PartRecord partRecord = new PartRecord();
        partRecord.setCreatedAt(parseDate("2021-04-01"));
        partRecord.setNormalPrice(2.0);
        partRecord.setSalePrice(2.0);
        partRecord.setUrgentPrice(2.0);

        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        partRecord.setDiscountRate(discountRate);

        List<PartRecord> partRecordList = new ArrayList<>();
        partRecordList.add(partRecord);

        partMocked.setPartRecords(partRecordList);
        partRecord.setPart(partMocked);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        partMocked.setProvider(provider);

        //act
        PartPriceDTO actual = mapper.toPartPriceDTO(partMocked);

        Assertions.assertEquals(expected, actual);
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

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
