package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.OrderDTO;
import com.bootcamp.finalProject.dtos.OrderRequestDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.exceptions.DeliveryStatusException;
import com.bootcamp.finalProject.exceptions.OrderIdNotFoundException;
import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.exceptions.SubsidiaryNotFoundException;
import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.repositories.ISubsidiaryRepository;
import com.bootcamp.finalProject.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static com.bootcamp.finalProject.utils.MapperUtils.getDifferencesInDays;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class WarehouseServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    ISubsidiaryRepository subsidiaryRepository;

    IWarehouseService warehouseService;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
        this.warehouseService = new WarehouseService(orderRepository, subsidiaryRepository);
    }

    @Test
    public void findSubsidiaryOrdersThrowsSubsidiaryNotFoundException() {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setDeliveryStatus("P");
        requestDTO.setDealerNumber(0L);

        //Act and Assert
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.empty());

        Assertions.assertThrows(SubsidiaryNotFoundException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }

    @Test
    public void findSubsidiaryOrdersThrowsSubsidiaryNotFoundExceptionDealerNumberNull() {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setDeliveryStatus("P");
        requestDTO.setDealerNumber(null);

        //Act and Assert
        Assertions.assertThrows(SubsidiaryNotFoundException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }

    @Test
    public void findSubsidiaryOrdersThrowsDeliveryStatusExceptionDiffValue() {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDeliveryStatus("A");

        //Act and Assert
        Assertions.assertThrows(DeliveryStatusException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }

    @Test
    public void findSubsidiaryOrdersDeliveryStatusNull() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setOrder(1);
        requestDTO.setDealerNumber(1L);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersDeliveryStatusP() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setDeliveryStatus("P");
        requestDTO.setOrder(1);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersDeliveryStatusD() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setDeliveryStatus("D");
        requestDTO.setOrder(1);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersDeliveryStatusF() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setDeliveryStatus("F");
        requestDTO.setOrder(1);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersDeliveryStatusC() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setDeliveryStatus("C");
        requestDTO.setOrder(1);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersThrowsOrderExceptionDiffValue() {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDeliveryStatus("C");
        requestDTO.setOrder(3);

        //Act and Assert
        Assertions.assertThrows(OrderTypeException.class, () -> warehouseService.findSubsidiaryOrders(requestDTO));
    }

    @Test
    public void findSubsidiaryOrdersByOrder0() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setOrder(0);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.ASC, "idOrder"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersByOrder1() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setOrder(1);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findSubsidiaryOrdersByOrder2() throws SubsidiaryNotFoundException, OrderTypeException, DeliveryStatusException {
        //Arrange
        OrderRequestDTO requestDTO = new OrderRequestDTO();
        requestDTO.setDealerNumber(1L);
        requestDTO.setOrder(2);

        Subsidiary subsidiary = new Subsidiary();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        expected.setDealerNumber("");
        expected.setOrders(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.DESC, "orderDate"))).thenReturn(new ArrayList<>());

        SubsidiaryResponseDTO actual = warehouseService.findSubsidiaryOrders(requestDTO);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findOrderByNumberThrowsSubsidiaryNotFoundException() {
        //Arrange
        String orderNumber = "0001-00000001";

        //Act and Assert
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(SubsidiaryNotFoundException.class, () -> warehouseService.findByOrderNumberCM(orderNumber));
    }

    @Test
    public void findOrderByNumberThrowsIdOrderNotFoundException() {
        //Arrange
        String orderNumber = "0001-00000001";
        Subsidiary subsidiary = new Subsidiary();

        //Act and Assert
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdOrderAndSubsidiary(1L, subsidiary)).thenReturn(Optional.empty());

        Assertions.assertThrows(OrderIdNotFoundException.class, () -> warehouseService.findByOrderNumberCM(orderNumber));
    }

    @Test
    public void findOrderByNumber() throws OrderIdNotFoundException, SubsidiaryNotFoundException {
        //Arrange
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");

        String orderNumber = "0001-00000001";
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setIdSubsidiary(1L);

        Order order = new Order();
        order.setIdOrder(1L);
        order.setOrderDate(new Date());
        order.setDeliveryDate(new Date());
        order.setDeliveredDate(new Date());
        order.setDeliveryStatus("C");
        order.setOrderDetails(new ArrayList<>());

        OrderDTO expected = new OrderDTO();
        expected.setOrderNumberCM(orderNumber);
        expected.setOrderDate(datePattern.format(order.getOrderDate()));
        expected.setDeliveryDate(datePattern.format(order.getDeliveryDate()));
        expected.setDeliveryStatus(order.getDeliveryStatus());
        expected.setDaysDelayed(getDifferencesInDays(order.getDeliveryDate(), order.getDeliveredDate()));
        expected.setOrderDetails(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdOrderAndSubsidiary(1L, subsidiary)).thenReturn(Optional.of(order));

        OrderDTO actual = warehouseService.findByOrderNumberCM(orderNumber);

        //Assert
        Assertions.assertEquals(expected, actual);
    }
}
