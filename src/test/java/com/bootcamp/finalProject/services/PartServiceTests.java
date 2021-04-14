package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartDTO;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class PartServiceTests {

    @InjectMocks
    PartService partService;

    @Mock
    PartRepository partRepository;

    @Mock
    PartResponseMapper mapper;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
    }

    @Test
    public void findPartThrowsExceptionTypeOfQueryNull() {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);

        //Act and Assert
        Assertions.assertThrows(TypeOfQueryException.class, () -> partService.findPart(requestDTO));
    }

    @Test
    public void findPartThrowsExceptionTypeOfQueryDiff() {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("A");

        //Act and Assert
        Assertions.assertThrows(TypeOfQueryException.class, () -> partService.findPart(requestDTO));
    }

    @Test
    public void findPartTypeOfQueryC() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setQueryType("C");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findAll(Sort.by(Sort.Direction.ASC, "description"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findPartTypeOfQueryP() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "description"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findPartTypeOfQueryV() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("V");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findByPriceCreateAt(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "description"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findPartThrowsExceptionOrderTypeDiff() {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setOrder(4);
        requestDTO.setQueryType("C");

        //Act and Assert
        Assertions.assertThrows(OrderTypeException.class, () -> partService.findPart(requestDTO));
    }

    @Test
    public void findPartOrderType0() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(0);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "partCode"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findPartOrderType1() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "description"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findPartOrderType2() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(2);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.DESC, "description"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findPartOrderType3() throws OrderTypeException, TypeOfQueryException {
        //Arrange
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(3);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();
        Part p1 = new Part();
        p1.setIdPart(1L);
        p1.setPartCode(1);
        p1.setDescription("Amortiguador derecho");
        p1.setWidthDimension(1);
        p1.setLongDimension(2);
        p1.setTallDimension(3);
        p1.setNetWeight(4);
        p1.setQuantity(10);
        p1.setLastModification(new Date());
        p1.setProvider(new Provider("Josecito", "", "", ""));
        ArrayList<PartRecord> partRecords = new ArrayList<>();
        PartRecord partRecord = new PartRecord();
        partRecord.setNormalPrice(100.0);
        partRecord.setUrgentPrice(140.0);
        partRecord.setDiscountRate(new DiscountRate());
        partRecord.getDiscountRate().setDiscount("A20");
        partRecords.add(partRecord);
        p1.setPartRecords(partRecords);

        PartResponseDTO response1 = new PartResponseDTO();
        response1.setPartCode(p1.getPartCode());
        response1.setDescription(p1.getDescription());
        response1.setMaker(p1.getProvider().getName());
        response1.setQuantity(p1.getQuantity());
        response1.setDiscountType(partRecord.getDiscountRate().getDiscount());
        response1.setNormalPrice(partRecord.getNormalPrice());
        response1.setUrgentPrice(partRecord.getUrgentPrice());
        response1.setNetWeight(p1.getNetWeight());
        response1.setLongDimension(p1.getLongDimension());
        response1.setWidthDimension(p1.getWidthDimension());
        response1.setTallDimension(p1.getTallDimension());
        response1.setLastModification(new SimpleDateFormat("yyyy-MM-dd").format(p1.getLastModification()));

        expected.add(response1);

        ArrayList<Part> partReturn = new ArrayList<>();
        partReturn.add(p1);

        //Act
        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.DESC, "lastModification"))).thenReturn(partReturn);
        when(mapper.toDTO(partReturn)).thenReturn(expected);

        List<PartResponseDTO> actual = partService.findPart(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updatePartNullPartCode() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(null);

        //act
        Assertions.assertThrows(IncorrectParamsGivenException.class, () -> partService.updatePart(requestPartDTO));
    }

    @Test
    public void updatePartPartNotExistException() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(0);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(null);

        //act
        Assertions.assertThrows(PartNotExistException.class, () -> partService.updatePart(requestPartDTO));
    }

    @Test
    public void updatePartDescription() throws InternalExceptionHandler {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setDescription("Amortiguador trasero izquierdo - BMW 220i - TEST");

        Part expectedPart = new Part();
        expectedPart.setIdPart(3L);
        expectedPart.setPartCode(11111114);
        expectedPart.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        expectedPart.setWidthDimension(15);
        expectedPart.setTallDimension(47);
        expectedPart.setLongDimension(47);
        expectedPart.setNetWeight(3200);
        expectedPart.setQuantity(140);
        expectedPart.setLastModification(parseDate("2021-01-05"));

        PartRecord partRecord = new PartRecord();
        partRecord.setIdPartRecord(7L);
        partRecord.setNormalPrice(35000.0);
        partRecord.setSalePrice(37500.0);
        partRecord.setUrgentPrice(39000.0);
        partRecord.setCreatedAt(parseDate("2021-01-05"));

        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        List<PartRecord> partRecordList = new ArrayList<>();
        partRecordList.add(partRecord);
        discountRate.setPartRecords(partRecordList);

        partRecord.setDiscountRate(discountRate);
        partRecord.setPart(expectedPart);

        expectedPart.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        expectedPart.setProvider(provider);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(expectedPart);
        when(partRepository.save(expectedPart)).thenReturn(expectedPart);

        //act
        Part actualPart = expectedPart.toBuilder().build();
        actualPart.setDescription(requestPartDTO.getDescription());
//        actualPart.setLastModification(new Date());
//      TODO: Averiguar como trabajar fecha en test para fechas autogeneradas en los procedimientos
        partService.updatePart(requestPartDTO);

        Assertions.assertEquals(actualPart, expectedPart);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
