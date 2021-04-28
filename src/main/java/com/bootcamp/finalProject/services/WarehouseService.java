package com.bootcamp.finalProject.services;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.mnemonics.BackOrderPriority;
import com.bootcamp.finalProject.mnemonics.BackOrderStatus;
import com.bootcamp.finalProject.mnemonics.DeliveryStatus;
import com.bootcamp.finalProject.model.*;
import com.bootcamp.finalProject.repositories.*;
import com.bootcamp.finalProject.utils.BackOrderMapper;
import com.bootcamp.finalProject.utils.OrderNumberCMUtil;
import com.bootcamp.finalProject.utils.OrderResponseMapper;
import com.bootcamp.finalProject.utils.SubsidiaryResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.bootcamp.finalProject.utils.MapperUtils.completeNumberByLength;
import static com.bootcamp.finalProject.utils.MapperUtils.getDifferencesInDays;
import static com.bootcamp.finalProject.utils.ValidationPartUtils.DSOrderTypeValidation;
import static com.bootcamp.finalProject.utils.ValidationPartUtils.deliveryStatusValidation;


@Service
public class WarehouseService implements IWarehouseService
{

    SubsidiaryResponseMapper subsidiaryMapper = new SubsidiaryResponseMapper();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ISubsidiaryRepository subsidiaryRepository;

    @Autowired
    private ISubsidiaryStockRepository subsidiaryStockRepository;

    @Autowired
    private IBackOrderRepository backOrderRepository;

    @Autowired
    private IUserService userService;

    @Override
    public SubsidiaryResponseDTO findSubsidiaryOrders(OrderRequestDTO orderRequest) throws OrderTypeException, DeliveryStatusException, SubsidiaryNotFoundException {
        List<Order> orders;
        Subsidiary subsidiary;
        if (deliveryStatusValidation(orderRequest.getDeliveryStatus())) {
            Sort sort = DSOrderTypeValidation(orderRequest.getOrder());
            Long idSubsidiary = orderRequest.getDealerNumber();
            subsidiary = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
            if (orderRequest.getDeliveryStatus() == null) {
                orders = orderRepository.findByIdSubsidiary(idSubsidiary, sort);
            } else {
                orders = orderRepository.findByIdSubsidiaryAndDeliveryStatus(idSubsidiary, orderRequest.getDeliveryStatus(), sort);
            }
            subsidiary.setOrders(orders);
        } else {
            throw new DeliveryStatusException();
        }
        return subsidiaryMapper.toOrderDTO(subsidiary);
    }

    @Override
    public OrderResponseDTO findByOrderNumberCM(String orderNumberCM) throws OrderIdNotFoundException, SubsidiaryNotFoundException {
        Long id = Long.valueOf(OrderNumberCMUtil.getNumberOR(orderNumberCM));

        Long idSubsidiary = Long.valueOf(OrderNumberCMUtil.getNumberCE(orderNumberCM));

        Subsidiary s = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
        Order o = orderRepository.findByIdOrderAndSubsidiary(id, s).orElseThrow(OrderIdNotFoundException::new);
        return new OrderResponseMapper().toOrderNumberCMDTO(o, s.getIdSubsidiary());
    }

    @Override
    public SubsidiaryStockResponseDTO findSubsidiaryStock(SubsidiaryStockRequestDTO subsidiaryStockRequestDTO) throws SubsidiaryNotFoundException {

        Subsidiary subsidiary = subsidiaryRepository.findById(subsidiaryStockRequestDTO.getDealerNumber()).orElseThrow(SubsidiaryNotFoundException::new);

        return new SubsidiaryResponseMapper().toStockDTO(subsidiary);
    }

    public void changeDeliveryStatus(String orderNumberCM, String newStatus) throws InternalExceptionHandler {
        Long orderId = Long.valueOf(OrderNumberCMUtil.getNumberOR(orderNumberCM));

        Long idSubsidiary = Long.valueOf(OrderNumberCMUtil.getNumberCE(orderNumberCM));
        Subsidiary subsidiary = subsidiaryRepository.findById(idSubsidiary).orElseThrow(SubsidiaryNotFoundException::new);
        Order order = orderRepository.findByIdOrderAndSubsidiary(orderId, subsidiary).orElseThrow(OrderIdNotFoundException::new);

        if ((order.getDeliveryStatus().equals(DeliveryStatus.PENDING)
                || order.getDeliveryStatus().equals(DeliveryStatus.DELAYED))){

            if (newStatus.equals(DeliveryStatus.CANCELED)){
                cancelDeliveryStatus(order);
            }else if (newStatus.equals(DeliveryStatus.FINISHED)){
                finishDeliveryStatus(order, subsidiary);
            }
            orderRepository.save(order);
        }else {
            //excepcion de que la orden habia sido cancelada o finalizada con anterioridad o
            throw new OrderDeliveryStatusIsconcludedException(order.getDeliveryStatus());
        }
    }

    public void cancelDeliveryStatus(Order order) throws PartAlreadyExistException {
        List<OrderDetail> orderDetail = order.getOrderDetails();

        orderDetail.forEach(partToUpdate -> {
            Part part = partRepository.findById(partToUpdate.getPartOrder().getIdPart()).orElseThrow();
            part.setQuantity(part.getQuantity()+partToUpdate.getQuantity());
            partRepository.save(part);
        });
        order.setDeliveryStatus(DeliveryStatus.CANCELED);
        orderRepository.save(order);
    }

    public OrderDTO newOrder(OrderDTO order, UserDetails user) throws InvalidAccountTypeExtensionException, NotEnoughStock, PartNotExistException
    {
        Subsidiary subsidiary = userService.getSubsidiaryByUsername(user);
        return createNewOrder(order, subsidiary);
    }

    private OrderDTO createNewOrder(OrderDTO order, Subsidiary subsidiary) throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        //esta hardcodeado cuanto tardo el envio.
        calendar.add(Calendar.DAY_OF_YEAR,3);
        Order orderReturn = new Order(null, current, calendar.getTime(), null, DeliveryStatus.PENDING, null, subsidiary);

        List<OrderDetail> orderList = new ArrayList<>();
        List<OrderDetailDTO> orderDetailList = order.getOrderDetails();
        for(OrderDetailDTO orderDetail : orderDetailList)
        {
            orderList.add(validateOrderToCreate(orderDetail,orderReturn));
            orderDetail.setReason(null);
        }
        orderReturn.setOrderDetails(orderList);
        orderRepository.save(orderReturn);
        for(OrderDetail orderDetail : orderReturn.getOrderDetails()){
            Part partStock = orderDetail.getPartOrder();
            partStock.setQuantity(partStock.getQuantity() - orderDetail.getQuantity());
            partRepository.save(partStock);
        }

        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
        //Set return with missing data.
        order.setOrderNumberCM(completeNumberByLength(String.valueOf(subsidiary.getIdSubsidiary()),4) + "-" + completeNumberByLength(String.valueOf(orderReturn.getIdOrder()),8));
        order.setDeliveryDate(datePattern.format(calendar.getTime()));
        order.setDeliveryStatus(DeliveryStatus.PENDING);
        order.setDaysDelayed(getDifferencesInDays(orderReturn.getDeliveryDate(),orderReturn.getDeliveredDate()));
        order.setOrderDate(datePattern.format(orderReturn.getOrderDate()));
        return order;
    }

    @Override
    public BackOrderDTO newBackOrder(BackOrderDTO backOrderDTO, UserDetails user) throws PartNotExistException, ThereIsAlredyStockException, InvalidAccountTypeExtensionException, InvalidBackOrderPriorityException {
        validatePriorityBackOrder(backOrderDTO.getBackOrderPriority());
        Subsidiary subsidiary = userService.getSubsidiaryByUsername(user);

        Date current = new Date();
        BackOrder backOrder = new BackOrder(current, BackOrderStatus.PENDING, backOrderDTO.getBackOrderPriority(), subsidiary);
        BackOrderDetail backOrderDetail = validateBackOrderToCreate(backOrderDTO.getBackOrderDetail(), backOrder);
        backOrder.setBackOrderDetail(backOrderDetail);
        backOrderRepository.save(backOrder);
        SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
        //Set return with missing data.
        backOrderDTO.setBackOrderNumberCM(completeNumberByLength(String.valueOf(subsidiary.getIdSubsidiary()),4) + "-" + completeNumberByLength(String.valueOf(backOrder.getIdBackOrder()),8));
        backOrderDTO.setBackOrderStatus(BackOrderStatus.PENDING);
        backOrderDTO.setBackOrderPriority(backOrder.getBackOrderPriority());
        backOrderDTO.setBackOrderDate(datePattern.format(backOrder.getBackOrderDate()));
        return backOrderDTO;
    }

    @Override
    public List<BackOrderDTO> finishBackOrder(Integer partCode) throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException {
        Part part =  partRepository.findByPartCode(partCode);
        List<BackOrderDTO> listResult = new ArrayList<>();
        if(part == null) {
            throw new PartNotExistException(partCode);
        }else{
            //first finish back order with high priority
            //priorisando la antiguedad del pedido
            //hasta que no haya mas productos con prioridad alta no avanzo con las back order de prioridad normal
            Date now = new Date();
            boolean noStock = false;
            ListIterator<BackOrder> backOrderList = backOrderRepository.findPendingBackOrderByPriorityOrderByBackOrderDateASC(BackOrderPriority.HIGH, part.getIdPart()).listIterator();
            createOrderFromBackOrder(backOrderList, part, listResult, partCode, noStock, now);
            if(!noStock && !backOrderList.hasNext()){
                backOrderList = backOrderRepository.findPendingBackOrderByPriorityOrderByBackOrderDateASC(BackOrderPriority.NORMAL, part.getIdPart()).listIterator();
                createOrderFromBackOrder(backOrderList, part, listResult, partCode, noStock, now);
                if(!noStock && !backOrderList.hasNext()){
                    backOrderList = backOrderRepository.findPendingBackOrderByPriorityOrderByBackOrderDateASC(BackOrderPriority.LOW, part.getIdPart()).listIterator();
                    createOrderFromBackOrder(backOrderList, part, listResult, partCode, noStock, now);
                }
            }
        }
        return listResult;
    }

    private void createOrderFromBackOrder(ListIterator<BackOrder> backOrderList, Part part, List<BackOrderDTO> listResult, Integer partCode, boolean noStock, Date now) throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException {
        while (!noStock && backOrderList.hasNext()){
            BackOrder elem = backOrderList.next();
            if(part == null){
                throw new PartNotExistException(partCode);
            }else{
                if(elem.getBackOrderDetail().getQuantity() > part.getQuantity()){
                    noStock = true;
                }else{
                    //creo nuevo orden
                    OrderDTO orderDTO = new OrderDTO();
                    List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

                    orderDetailDTO.setPartCode(elem.getBackOrderDetail().getPartBackOrder().getPartCode().toString());
                    orderDetailDTO.setAccountType(elem.getBackOrderDetail().getAccountType());
                    orderDetailDTO.setQuantity(elem.getBackOrderDetail().getQuantity());
                    orderDetailDTOList.add(orderDetailDTO);
                    orderDTO.setOrderDetails(orderDetailDTOList);
                    createNewOrder(orderDTO, elem.getSubsidiary());
                    //cambio estado backorder
                    elem.setBackOrderStatus(BackOrderStatus.FINISHED);
                    elem.setFinishBackOrderDate(now);
                    backOrderRepository.save(elem);
                    //agrego a lista resultDTO
                    listResult.add(BackOrderMapper.toDTO(elem, orderDTO.getOrderNumberCM()));
                    //elimino de la lista
                    backOrderList.remove();
                    part =  partRepository.findByPartCode(partCode);
                }
            }
        }
    }

    private OrderDetail validateOrderToCreate(OrderDetailDTO orderDetail, Order orderReturn) throws PartNotExistException, NotEnoughStock, InvalidAccountTypeExtensionException
    {
        OrderDetail orderDetailReturn;
        Part part =  partRepository.findByPartCode(Integer.parseInt(orderDetail.getPartCode()));
        if(part == null)
        {
            throw new PartNotExistException(Integer.parseInt(orderDetail.getPartCode()));
        }
        else
        {
            if(part.getQuantity() < orderDetail.getQuantity())
            {
                throw new NotEnoughStock(orderDetail.getPartCode());
            }
            else
            {
                orderDetail.setDescription(part.getDescription());

                if(orderDetail.getAccountType() == null || orderDetail.getAccountType().length() != 1)
                {
                    throw new InvalidAccountTypeExtensionException();
                }
            }

            orderDetailReturn = new OrderDetail(null, orderDetail.getAccountType(), orderDetail.getQuantity(), null , part, orderReturn);
        }
        
        return orderDetailReturn;
    }

    public void finishDeliveryStatus(Order order, Subsidiary subsidiary){
        List<OrderDetail> orderDetails = order.getOrderDetails();
        SubsidiaryStock temp;
        for(OrderDetail o :orderDetails){
            temp = subsidiaryStockRepository.findByIdPart(o.getPartOrder().getIdPart(),subsidiary.getIdSubsidiary());
            if(temp==null){
                SubsidiaryStock subsidiaryStock = new SubsidiaryStock();
                subsidiaryStock.setQuantity(0);
                subsidiaryStock.setPart(o.getPartOrder());
                subsidiaryStock.setSubsidiary(subsidiary);
                subsidiaryStockRepository.save(subsidiaryStock);
                temp = subsidiaryStock;
            }
            temp.setQuantity(temp.getQuantity()+o.getQuantity());
            subsidiaryStockRepository.save(temp);
        }
        Date now = new Date();
        order.setDeliveryStatus(DeliveryStatus.FINISHED);
        order.setDeliveredDate(now);
    }

    private BackOrderDetail validateBackOrderToCreate(BackOrderDetailDTO backOrderDetailDTO, BackOrder backOrder) throws PartNotExistException, ThereIsAlredyStockException, InvalidAccountTypeExtensionException {
        BackOrderDetail backOrderDetail;
        Part part =  partRepository.findByPartCode(backOrderDetailDTO.getPartCode());
        if(part == null) {
            throw new PartNotExistException(backOrderDetailDTO.getPartCode());
        }else{
            if(part.getQuantity() >= backOrderDetailDTO.getQuantity())
            {
                throw new ThereIsAlredyStockException(backOrderDetailDTO.getPartCode());
            }
            else
            {
                backOrderDetailDTO.setDescription(part.getDescription());

                if(backOrderDetailDTO.getAccountType() == null || backOrderDetailDTO.getAccountType().length() != 1 ||
                        (!backOrderDetailDTO.getAccountType().equalsIgnoreCase("R") && !backOrderDetailDTO.getAccountType().equalsIgnoreCase("G")))
                {
                    throw new InvalidAccountTypeExtensionException();
                }
            }
            backOrderDetail = new BackOrderDetail(backOrderDetailDTO.getAccountType(), backOrderDetailDTO.getQuantity(), part, backOrder);
        }
        return backOrderDetail;
    }

    private void validatePriorityBackOrder(String priority) throws InvalidBackOrderPriorityException {
        if(priority == null || priority.length() != 1 ||
                (!priority.equalsIgnoreCase(BackOrderPriority.HIGH) && !priority.equalsIgnoreCase(BackOrderPriority.NORMAL) && !priority.equalsIgnoreCase(BackOrderPriority.LOW)))
        {
            throw new InvalidBackOrderPriorityException();
        }
    }
}