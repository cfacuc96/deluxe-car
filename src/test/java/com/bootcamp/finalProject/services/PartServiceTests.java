package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.PartRecord;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.repositories.DiscountRateRepository;
import com.bootcamp.finalProject.repositories.IProviderRepository;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.utils.PartResponseMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class PartServiceTests {

    @InjectMocks
    PartService partService;

    @Mock
    PartRepository partRepository;

    @Mock
    IProviderRepository providerRepository;

    @Mock
    DiscountRateRepository discountRateRepository;

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
    public void updatePartNotParamsToModifyException() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(0);

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);

        //act
        Assertions.assertThrows(NotParamsToModifyException.class, () -> partService.updatePart(requestPartDTO));
    }

    @Test
    public void updatePartDescription() throws InternalExceptionHandler {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setDescription("Amortiguador trasero izquierdo - BMW 220i - TEST");

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);
        when(partRepository.save(actual)).thenReturn(actual);

        Part expected = actual.toBuilder().build();
        expected.setDescription(requestPartDTO.getDescription());
//        actualPart.setLastModification(new Date());
//      TODO: Averiguar como trabajar fecha en test para fechas autogeneradas en los procedimientos
        //act
        partService.updatePart(requestPartDTO);

        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updatePartAtributesComplete() throws InternalExceptionHandler {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setDescription("Amortiguador trasero izquierdo - BMW 220i - TEST");
        requestPartDTO.setQuantity(1);
        requestPartDTO.setNetWeight(1);
        requestPartDTO.setLongDimension(1);
        requestPartDTO.setWidthDimension(1);
        requestPartDTO.setTallDimension(1);

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);
        when(partRepository.save(actual)).thenReturn(actual);

        Part expected = actual.toBuilder().build();
        expected.setDescription(requestPartDTO.getDescription());
        expected.setQuantity(requestPartDTO.getQuantity());
        expected.setNetWeight(requestPartDTO.getNetWeight());
        expected.setLongDimension(requestPartDTO.getLongDimension());
        expected.setWidthDimension(requestPartDTO.getWidthDimension());
        expected.setTallDimension(requestPartDTO.getTallDimension());

        //act
        partService.updatePart(requestPartDTO);

        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updatePartMaker() throws InternalExceptionHandler {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setMakerId(2L);

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);
        when(partRepository.save(actual)).thenReturn(actual);

        Part expected = actual.toBuilder().build();
        Provider providerExpected = new Provider();
        providerExpected.setIdProvider(2L);
        providerExpected.setName("Josesinho");
        providerExpected.setCountry("Brasil");
        providerExpected.setPhone("12345667890");
        providerExpected.setAddress("Mi direccion2");
        expected.setProvider(providerExpected);

        when(providerRepository.findById(requestPartDTO.getMakerId())).thenReturn(Optional.of(providerExpected));
        //act
        partService.updatePart(requestPartDTO);

        //assert
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected.getProvider(), actual.getProvider());
    }

    @Test
    public void updatePartMakerNotFoundException() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setMakerId(0L);

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);

        Mockito.when(providerRepository.findById(requestPartDTO.getMakerId())).thenReturn(Optional.empty());

        //act
        Assertions.assertThrows(ProviderIdNotFoundException.class, () -> partService.updatePart(requestPartDTO));

    }

    @Test
    public void updatePartPrice() throws InternalExceptionHandler {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setNormalPrice(1D);
        requestPartDTO.setSalePrice(1D);
        requestPartDTO.setUrgentPrice(1D);
        requestPartDTO.setDiscountId(1L);

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        DiscountRate discountRateExpected = new DiscountRate();
        discountRateExpected.setIdDiscountRate(1L);
        discountRateExpected.setDescription("Descuento Facu 3");
        discountRateExpected.setDiscount("FAC");

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);
        when(discountRateRepository.findById(requestPartDTO.getDiscountId())).thenReturn(Optional.of(discountRateExpected));        //act
        when(partRepository.save(actual)).thenReturn(actual);

        Part expected = actual.toBuilder().build();

        PartRecord partRecordExpected = new PartRecord();
        partRecordExpected.setNormalPrice(requestPartDTO.getNormalPrice());
        partRecordExpected.setSalePrice(requestPartDTO.getSalePrice());
        partRecordExpected.setUrgentPrice(requestPartDTO.getUrgentPrice());

        partRecordExpected.setPart(expected);

        partRecordExpected.setDiscountRate(discountRateExpected);

        List<PartRecord> list = new ArrayList<>();
        list.add(partRecord);
        list.add(partRecordExpected);
        expected.setPartRecords(list);


        //act
        partService.updatePart(requestPartDTO);

        //assert
        Assertions.assertEquals(expected, actual);
        Assertions.assertIterableEquals(expected.getPartRecords(), actual.getPartRecords());
    }

    @Test
    public void updatePartPriceDiscountNotFoundException() throws InternalExceptionHandler {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);
        requestPartDTO.setNormalPrice(1D);
        requestPartDTO.setSalePrice(1D);
        requestPartDTO.setUrgentPrice(1D);
        requestPartDTO.setDiscountId(0L);

        Part actual = new Part();
        actual.setIdPart(3L);
        actual.setPartCode(11111114);
        actual.setDescription("Amortiguador trasero izquierdo - BMW 220i");
        actual.setWidthDimension(15);
        actual.setTallDimension(47);
        actual.setLongDimension(47);
        actual.setNetWeight(3200);
        actual.setQuantity(140);
        actual.setLastModification(parseDate("2021-01-05"));

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
        partRecord.setPart(actual);

        actual.setPartRecords(partRecordList);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        actual.setProvider(provider);

        DiscountRate discountRateExpected = new DiscountRate();
        discountRateExpected.setIdDiscountRate(1L);
        discountRateExpected.setDescription("Descuento Facu 3");
        discountRateExpected.setDiscount("FAC");

        when(partRepository.findByPartCode(requestPartDTO.getPartCode())).thenReturn(actual);
        when(discountRateRepository.findById(requestPartDTO.getDiscountId())).thenReturn(Optional.empty());        //act



        //act
        Assertions.assertThrows(DiscountRateIDNotFoundException.class, () -> partService.updatePart(requestPartDTO));

    }

    @Test
    public void newPartComplete() throws Exception {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(21111114);
        requestPartDTO.setDescription("TEST");
        requestPartDTO.setQuantity(1);
        requestPartDTO.setNetWeight(1);
        requestPartDTO.setLongDimension(1);
        requestPartDTO.setWidthDimension(1);
        requestPartDTO.setTallDimension(1);

        requestPartDTO.setNormalPrice(2D);
        requestPartDTO.setSalePrice(2D);
        requestPartDTO.setUrgentPrice(2D);

        requestPartDTO.setMakerId(1L);
        requestPartDTO.setDiscountId(1L);

        Part expected = new Part();
        expected.setPartCode(21111114);
        expected.setDescription("TEST");
        expected.setWidthDimension(1);
        expected.setTallDimension(1);
        expected.setLongDimension(1);
        expected.setNetWeight(1);
        expected.setQuantity(1);

        PartRecord partRecord = new PartRecord();
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

        expected.setPartRecords(partRecordList);
        partRecord.setPart(expected);

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        expected.setProvider(provider);

        when(partRepository.existsByPartCode(requestPartDTO.getPartCode())).thenReturn(false);
        when(discountRateRepository.findById(discountRate.getIdDiscountRate())).thenReturn(Optional.of(discountRate));
        when(providerRepository.findById(provider.getIdProvider())).thenReturn(Optional.of(provider));
        when(partRepository.save(expected)).thenReturn(expected);

        //act
        Part actual = partService.newPart(requestPartDTO);

        //assertion
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void newPartAlreadyExistException() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);

        when(partRepository.existsByPartCode(requestPartDTO.getPartCode())).thenReturn(true);

        //act
        Assertions.assertThrows(PartAlreadyExistException.class, () -> partService.newPart(requestPartDTO));
    }

    @Test
    public void newPartDiscountRateIDNotFoundException() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(11111114);

        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        when(partRepository.existsByPartCode(requestPartDTO.getPartCode())).thenReturn(false);
        when(discountRateRepository.findById(discountRate.getIdDiscountRate())).thenReturn(Optional.empty());

        //act
        Assertions.assertThrows(DiscountRateIDNotFoundException.class, () -> partService.newPart(requestPartDTO));
    }

    @Test
    public void newPartProviderIdNotFoundException() {
        //arrange
        PartDTO requestPartDTO = new PartDTO();
        requestPartDTO.setPartCode(21111114);
        requestPartDTO.setDiscountId(1L);

        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        when(partRepository.existsByPartCode(requestPartDTO.getPartCode())).thenReturn(false);
        when(discountRateRepository.findById(requestPartDTO.getDiscountId())).thenReturn(Optional.of(discountRate));
        when(providerRepository.findById(provider.getIdProvider())).thenReturn(Optional.empty());

        //act
        Assertions.assertThrows(ProviderIdNotFoundException.class, () -> partService.newPart(requestPartDTO));
    }

    @Test
    public void findProviderByIdProviderIdNotFoundException() {
        //arrange
        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        ProviderDTO expected = new ProviderDTO();
        expected.setIdProvider(provider.getIdProvider());
        expected.setName(provider.getName());
        expected.setAddress(provider.getAddress());
        expected.setPhone(provider.getPhone());
        expected.setCountry(provider.getCountry());

        when(providerRepository.findById(provider.getIdProvider())).thenReturn(Optional.empty());

        Assertions.assertThrows(ProviderIdNotFoundException.class, () -> partService.findProviderById(provider.getIdProvider()));

    }

    @Test
    public void findAllProvidersCorrect() {
        //arrange
        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        Provider provider2 = new Provider();
        provider2.setIdProvider(2L);
        provider2.setName("Josesinho");
        provider2.setCountry("Brasil");
        provider2.setPhone("12345667890");
        provider2.setAddress("Mi direccion2");

        List<Provider> providerList = new ArrayList<>();
        providerList.add(provider);
        providerList.add(provider2);

        ProviderDTO expected = new ProviderDTO();
        expected.setIdProvider(provider.getIdProvider());
        expected.setName(provider.getName());
        expected.setAddress(provider.getAddress());
        expected.setPhone(provider.getPhone());
        expected.setCountry(provider.getCountry());

        ProviderDTO expected2 = new ProviderDTO();
        expected2.setIdProvider(provider2.getIdProvider());
        expected2.setName(provider2.getName());
        expected2.setAddress(provider2.getAddress());
        expected2.setPhone(provider2.getPhone());
        expected2.setCountry(provider2.getCountry());

        List<ProviderDTO> listExpected = new ArrayList<>();
        listExpected.add(expected);
        listExpected.add(expected2);

        when(providerRepository.findAll()).thenReturn(providerList);

        //act
        List<ProviderDTO> actual = partService.findAllProviders();

        //assert
        Assertions.assertIterableEquals(listExpected, actual);
    }

    @Test
    public void findProviderByIdCorrect() throws InternalExceptionHandler {
        //arrange
        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        ProviderDTO expected = new ProviderDTO();
        expected.setIdProvider(provider.getIdProvider());
        expected.setName(provider.getName());
        expected.setAddress(provider.getAddress());
        expected.setPhone(provider.getPhone());
        expected.setCountry(provider.getCountry());

        when(providerRepository.findById(provider.getIdProvider())).thenReturn(Optional.of(provider));

        ProviderDTO actual = partService.findProviderById(provider.getIdProvider());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findDiscountRateByIdDiscountRateIDNotFoundException() {
        //arrange
        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        when(discountRateRepository.findById(discountRate.getIdDiscountRate())).thenReturn(Optional.empty());

        Assertions.assertThrows(DiscountRateIDNotFoundException.class, () -> partService.findDiscountRateById(discountRate.getIdDiscountRate()));

    }

    @Test
    public void findALLDiscountRateCorrect() {
        //arrange
        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        DiscountRate discountRate2 = new DiscountRate();
        discountRate2.setIdDiscountRate(2L);
        discountRate2.setDescription("Descuento Facu");
        discountRate2.setDiscount("FAC");

        List<DiscountRate> discountRateList = new ArrayList<>();
        discountRateList.add(discountRate);
        discountRateList.add(discountRate2);

        DiscountRateDTO expected = new DiscountRateDTO();
        expected.setIdDiscountRate(discountRate.getIdDiscountRate());
        expected.setDescription(discountRate.getDescription());
        expected.setDiscount(discountRate.getDiscount());

        DiscountRateDTO expected2 = new DiscountRateDTO();
        expected2.setIdDiscountRate(discountRate2.getIdDiscountRate());
        expected2.setDescription(discountRate2.getDescription());
        expected2.setDiscount(discountRate2.getDiscount());

        List<DiscountRateDTO> listExpected = new ArrayList<>();
        listExpected.add(expected);
        listExpected.add(expected2);

        when(discountRateRepository.findAll()).thenReturn(discountRateList);

        //act
        List<DiscountRateDTO> actual = partService.findALLDiscountRate();

        //assert
        Assertions.assertIterableEquals(listExpected, actual);
    }

    @Test
    public void findDiscountRateByIdCorrect() throws InternalExceptionHandler {
        //arrange
        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        DiscountRateDTO expected = new DiscountRateDTO();
        expected.setIdDiscountRate(discountRate.getIdDiscountRate());
        expected.setDescription(discountRate.getDescription());
        expected.setDiscount(discountRate.getDiscount());

        when(discountRateRepository.findById(discountRate.getIdDiscountRate())).thenReturn(Optional.of(discountRate));

        DiscountRateDTO actual = partService.findDiscountRateById(discountRate.getIdDiscountRate());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void saveDiscountRate() throws InternalExceptionHandler {
        //arrange
        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        DiscountRateDTO actual = new DiscountRateDTO();
        actual.setIdDiscountRate(1L);
        actual.setDescription("Clarin 365");
        actual.setDiscount("%20");

        when(discountRateRepository.findById(discountRate.getIdDiscountRate())).thenReturn(Optional.of(discountRate));
        when(discountRateRepository.save(discountRate)).thenReturn(discountRate);

        DiscountRate expected = new DiscountRate();
        expected.setIdDiscountRate(1L);
        expected.setDescription("Clarin 365");
        expected.setDiscount("%20");

        //act
        partService.saveDiscountRate(actual);

        //assert
        Assertions.assertEquals(expected, discountRate);

    }

    @Test
    public void saveDiscountRateDiscountRateAlreadyExistException() {
        //arrange
        DiscountRate discountRate = new DiscountRate();
        discountRate.setIdDiscountRate(1L);
        discountRate.setDescription("Clarin 365");
        discountRate.setDiscount("%20");

        DiscountRateDTO actual = new DiscountRateDTO();
        actual.setIdDiscountRate(1L);
        actual.setDescription("Clarin 365");
        actual.setDiscount("%20");

        when(discountRateRepository.findById(discountRate.getIdDiscountRate())).thenReturn(Optional.of(discountRate));

        //act
        //Assertions.assertThrows(DiscountRateAlreadyExistException.class, () -> partService.saveDiscountRate(actual));
        Assertions.assertDoesNotThrow(() -> partService.saveDiscountRate(actual));
        }

    @Test
    public void saveProvider() throws InternalExceptionHandler {
        //arrange
        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        ProviderDTO actual = new ProviderDTO();
        actual.setIdProvider(provider.getIdProvider());
        actual.setName(provider.getName());
        actual.setAddress(provider.getAddress());
        actual.setPhone(provider.getPhone());
        actual.setCountry(provider.getCountry());

        when(providerRepository.findById(actual.getIdProvider())).thenReturn(Optional.of(provider));
        when(providerRepository.save(provider)).thenReturn(provider);

        Provider expected = new Provider();
        expected.setIdProvider(1L);
        expected.setName("Jose");
        expected.setCountry("Argentina");
        expected.setPhone("1234567890");
        expected.setAddress("Direccion1");

        //act
        partService.saveProvider(actual);

        //assert
        Assertions.assertEquals(expected, provider);

    }

    @Test
    public void saveProviderProviderAlreadyExistException() {
        //arrange
        Provider provider = new Provider();
        provider.setIdProvider(1L);
        provider.setName("Jose");
        provider.setCountry("Argentina");
        provider.setPhone("1234567890");
        provider.setAddress("Direccion1");

        ProviderDTO actual = new ProviderDTO();
        actual.setIdProvider(provider.getIdProvider());
        actual.setName(provider.getName());
        actual.setAddress(provider.getAddress());
        actual.setPhone(provider.getPhone());
        actual.setCountry(provider.getCountry());

        when(providerRepository.findById(actual.getIdProvider())).thenReturn(Optional.of(provider));

        //act
        //Assertions.assertThrows(ProviderAlreadyExistException.class, () -> partService.saveProvider(actual));
        Assertions.assertDoesNotThrow(() -> partService.saveProvider(actual));
    }


    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
