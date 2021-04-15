package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.model.Order;
import com.bootcamp.finalProject.model.OrderDetail;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.model.Subsidiary;
import com.bootcamp.finalProject.repositories.ISubsidiaryRepository;
import com.bootcamp.finalProject.repositories.OrderRepository;
import com.bootcamp.finalProject.utils.SubsidiaryResponseMapper;
import com.bootcamp.finalProject.repositories.PartRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class WarehouseServiceTest {

    @InjectMocks
    WarehouseService warehouseService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    PartRepository partRepository;

    @Mock
    ISubsidiaryRepository subsidiaryRepository;
    @Mock
    SubsidiaryResponseMapper mapper;

    @BeforeEach
    void initSetUp() {
        openMocks(this);
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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiaryAndDeliveryStatus(requestDTO.getDealerNumber(), requestDTO.getDeliveryStatus(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.ASC, "idOrder"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.ASC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        List<Order> orders = new ArrayList<>();

        SubsidiaryResponseDTO expected = new SubsidiaryResponseDTO();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        expected.setDealerNumber("");
        expected.setOrders(ordersDTO);

        //Act
        when(subsidiaryRepository.findById(requestDTO.getDealerNumber())).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdSubsidiary(requestDTO.getDealerNumber(), Sort.by(Sort.Direction.DESC, "orderDate"))).thenReturn(orders);
        when(mapper.toOrderDTO(subsidiary)).thenReturn(expected);

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
        order.setDeliveryStatus("C");
        order.setOrderDetails(new ArrayList<>());

        OrderResponseDTO expected = new OrderResponseDTO();
        expected.setOrderNumberCM(orderNumber);
        expected.setOrderDate(datePattern.format(order.getOrderDate()));
        expected.setDeliveryStatus(order.getDeliveryStatus());
        expected.setOrderDetails(new ArrayList<>());

        //Act
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.findByIdOrderAndSubsidiary(1L, subsidiary)).thenReturn(Optional.of(order));

        OrderResponseDTO actual = warehouseService.findByOrderNumberCM(orderNumber);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void newOrder() throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException {
        //aca hace falta mockear el subsidiary.
        Subsidiary subsidiary = new Subsidiary();

        //Arrange
        Order order = new Order();
        order.setIdOrder(1L);
        order.setSubsidiary(subsidiary);
        order.setOrderDate(new Date());
        order.setDeliveryStatus("P");
        OrderDetail orderDetail = new OrderDetail();
        Part part = new Part();
        part.setPartCode(11111112);
        part.setQuantity(30);
        part.setDescription("Amortiguador delantero derecho - BMW 220i");
        orderDetail.setPartOrder(part);
        orderDetail.setQuantity(20);
        orderDetail.setAccountType("R");
        orderDetail.setIdOrderDetail(18L);
        List<OrderDetail> orderList = new ArrayList<>();
        orderList.add(orderDetail);
        order.setOrderDetails(orderList);

        OrderDTO orderDTOExpected = new OrderDTO();
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
        orderDTOExpected.setOrderDate(datePattern.format(new Date()));
        orderDTOExpected.setDeliveryStatus("P");
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        PartDTO partDTO = new PartDTO();
        partDTO.setPartCode(11111112);
        partDTO.setDescription("Amortiguador delantero derecho - BMW 220i");
        partDTO.setQuantity(30);
        orderDetailDTO.setPartCode(String.valueOf(partDTO.getPartCode()));
        orderDetailDTO.setQuantity(20);
        orderDetailDTO.setAccountType("R");
        orderDetailDTO.setDescription(partDTO.getDescription());
        List<OrderDetailDTO> orderListDTO = new ArrayList<>();
        orderListDTO.add(orderDetailDTO);
        orderDTOExpected.setOrderDetails(orderListDTO);

        //Act
        when(partRepository.findByPartCode(11111112)).thenReturn(part);
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.save(order)).thenReturn(order);

        OrderDTO actual = warehouseService.newOrder(orderDTOExpected);

        //Assert
        Assertions.assertEquals(orderDTOExpected, actual);
    }

    @Test
    public void newOrderWithoutPart()throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException
    {
        //aca hace falta mockear el subsidiary.
        Subsidiary subsidiary = new Subsidiary();

        //Arrange
        Order order = new Order();
        order.setIdOrder(1L);
        order.setSubsidiary(subsidiary);
        order.setOrderDate(new Date());
        order.setDeliveryStatus("P");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(20);
        orderDetail.setAccountType("R");
        orderDetail.setIdOrderDetail(18L);
        List<OrderDetail> orderList = new ArrayList<>();
        orderList.add(orderDetail);
        order.setOrderDetails(orderList);

        OrderDTO orderDTO = new OrderDTO();
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
        orderDTO.setOrderDate(datePattern.format(new Date()));
        orderDTO.setDeliveryStatus("P");
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        PartDTO partDTO = new PartDTO();
        partDTO.setPartCode(11111112);
        partDTO.setDescription("Amortiguador delantero derecho - BMW 220i");
        partDTO.setQuantity(30);
        orderDetailDTO.setPartCode(String.valueOf(partDTO.getPartCode()));
        orderDetailDTO.setQuantity(20);
        orderDetailDTO.setAccountType("R");
        orderDetailDTO.setDescription(partDTO.getDescription());
        List<OrderDetailDTO> orderListDTO = new ArrayList<>();
        orderListDTO.add(orderDetailDTO);
        orderDTO.setOrderDetails(orderListDTO);

        //Act
        when(partRepository.findByPartCode(11111112)).thenReturn(null);
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.save(order)).thenReturn(order);

        //Assert
        Assertions.assertThrows(PartNotExistException.class, () -> warehouseService.newOrder(orderDTO));
    }

    @Test
    public void newOrderNotEnoughStock()throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException
    {
        //aca hace falta mockear el subsidiary.
        Subsidiary subsidiary = new Subsidiary();

        //Arrange
        Order order = new Order();
        order.setIdOrder(1L);
        order.setSubsidiary(subsidiary);
        order.setOrderDate(new Date());
        order.setDeliveryStatus("P");
        Part part = new Part();
        part.setPartCode(11111112);
        part.setQuantity(0);
        part.setDescription("Amortiguador delantero derecho - BMW 220i");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPartOrder(part);
        orderDetail.setQuantity(20);
        orderDetail.setAccountType("R");
        orderDetail.setIdOrderDetail(18L);
        List<OrderDetail> orderList = new ArrayList<>();
        orderList.add(orderDetail);
        order.setOrderDetails(orderList);

        OrderDTO orderDTO = new OrderDTO();
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
        orderDTO.setOrderDate(datePattern.format(new Date()));
        orderDTO.setDeliveryStatus("P");
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        PartDTO partDTO = new PartDTO();
        partDTO.setPartCode(11111112);
        partDTO.setDescription("Amortiguador delantero derecho - BMW 220i");
        partDTO.setQuantity(30);
        orderDetailDTO.setPartCode(String.valueOf(partDTO.getPartCode()));
        orderDetailDTO.setQuantity(20);
        orderDetailDTO.setAccountType("R");
        orderDetailDTO.setDescription(partDTO.getDescription());
        List<OrderDetailDTO> orderListDTO = new ArrayList<>();
        orderListDTO.add(orderDetailDTO);
        orderDTO.setOrderDetails(orderListDTO);

        //Act
        when(partRepository.findByPartCode(11111112)).thenReturn(part);
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.save(order)).thenReturn(order);

        //Assert
        Assertions.assertThrows(NotEnoughStock.class, () -> warehouseService.newOrder(orderDTO));
    }
    @Test
    public void newOrderInvalidAccountType()throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException
    {
        //aca hace falta mockear el subsidiary.
        Subsidiary subsidiary = new Subsidiary();

        //Arrange
        Order order = new Order();
        order.setIdOrder(1L);
        order.setSubsidiary(subsidiary);
        order.setOrderDate(new Date());
        order.setDeliveryStatus("P");
        Part part = new Part();
        part.setPartCode(11111112);
        part.setQuantity(30);
        part.setDescription("Amortiguador delantero derecho - BMW 220i");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPartOrder(part);
        orderDetail.setQuantity(20);
        orderDetail.setIdOrderDetail(18L);
        List<OrderDetail> orderList = new ArrayList<>();
        orderList.add(orderDetail);
        order.setOrderDetails(orderList);

        OrderDTO orderDTO = new OrderDTO();
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
        orderDTO.setOrderDate(datePattern.format(new Date()));
        orderDTO.setDeliveryStatus("P");
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        PartDTO partDTO = new PartDTO();
        partDTO.setPartCode(11111112);
        partDTO.setDescription("Amortiguador delantero derecho - BMW 220i");
        partDTO.setQuantity(30);
        orderDetailDTO.setPartCode(String.valueOf(partDTO.getPartCode()));
        orderDetailDTO.setQuantity(20);
        orderDetailDTO.setDescription(partDTO.getDescription());
        List<OrderDetailDTO> orderListDTO = new ArrayList<>();
        orderListDTO.add(orderDetailDTO);
        orderDTO.setOrderDetails(orderListDTO);

        //Act
        when(partRepository.findByPartCode(11111112)).thenReturn(part);
        when(subsidiaryRepository.findById(1L)).thenReturn(Optional.of(subsidiary));
        when(orderRepository.save(order)).thenReturn(order);

        //Assert
        Assertions.assertThrows(InvalidAccountTypeExtensionException.class, () -> warehouseService.newOrder(orderDTO));
    }
}
