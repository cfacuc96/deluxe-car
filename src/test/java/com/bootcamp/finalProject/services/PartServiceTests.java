package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.TypeOfQueryException;
import com.bootcamp.finalProject.repositories.PartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class PartServiceTests {

    @Mock
    PartRepository partRepository;

    IPartService partService;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
        partService = new PartService(partRepository);
    }

    @Test
    public void findPartThrowsExceptionTypeOfQueryNull() {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);

        Assertions.assertThrows(TypeOfQueryException.class, () -> partService.findPart(requestDTO));
    }

    @Test
    public void findPartThrowsExceptionTypeOfQueryDiff() {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("A");

        Assertions.assertThrows(TypeOfQueryException.class, () -> partService.findPart(requestDTO));
    }

    @Test
    public void findPartTypeOfQueryC() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setQueryType("C");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findAll(Sort.by(Sort.Direction.ASC, "partCode"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }

    @Test
    public void findPartTypeOfQueryP() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "partCode"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }

    @Test
    public void findPartTypeOfQueryV() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("V");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findByPriceCreateAt(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "partCode"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }

    @Test
    public void findPartThrowsExceptionOrderTypeDiff() {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setOrder(4);
        requestDTO.setQueryType("C");

        Assertions.assertThrows(OrderTypeException.class, () -> partService.findPart(requestDTO));
    }

    @Test
    public void findPartOrderType0() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(0);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "partCode"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }

    @Test
    public void findPartOrderType1() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(1);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.ASC, "description"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }

    @Test
    public void findPartOrderType2() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(2);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.DESC, "description"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }

    @Test
    public void findPartOrderType3() throws OrderTypeException, TypeOfQueryException {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setDate(new Date());
        requestDTO.setOrder(3);
        requestDTO.setQueryType("P");

        ArrayList<PartResponseDTO> expected = new ArrayList<>();

        when(partRepository.findByLastModification(requestDTO.getDate(), Sort.by(Sort.Direction.DESC, "lastModification"))).thenReturn(new ArrayList<>());

        Assertions.assertEquals(expected, partService.findPart(requestDTO));
    }
}
