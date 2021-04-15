package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.*;
import com.bootcamp.finalProject.mnemonics.OrderType;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import com.bootcamp.finalProject.utils.ValidationController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

import static com.bootcamp.finalProject.utils.ValidationController.isListEndpointMapValid;
import static com.bootcamp.finalProject.utils.ValidationController.validateDateFormat;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController extends CentralController{

    @Autowired
    IPartService service;

    @Autowired
    IWarehouseService warehouseService;

    /**
     * GET method to search list of parts, it receives a map with the following data
     * queryType: [“C”,”P”,”V”] -> COMPLETE, PARTIAL, VARIATION
     * date:  date for query consultation
     * order: [”0”,1”,”2”,”3”] -> orderDate default, orderDate ASC, orderDate DESC, orderDate LastChange
     *
     * @param params map of parameters given by user
     * @return List<PartResponseDTO> that contains the list of parts that have had a change according to the query
     */
    @GetMapping("list")
    @ApiOperation(
            value = "Return a list of Parts based on the query",
            nickname = "Find Parts by Query"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PartResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "FORBIDDEN")

    }
    )
    public List<PartResponseDTO> findPart(
            @ApiParam(value = "* queryType: [“C”,”P”,”V”] -> COMPLETE, PARTIAL, VARIATION\n" +
                    "* date:  date for query consultation\n" +
                    "* order: [”0”,1”,”2”,”3”] -> orderDate default, orderDate ASC, orderDate DESC, orderDate LastChange", required = true)
            @Nullable @RequestParam Map<String, String> params) throws InternalExceptionHandler {
        //Validations
        isListEndpointMapValid(params);
        //Set parameters for PartRequestDto
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setQueryType(params.get("queryType"));
        requestDTO.setDate((params.get("date") == null) ? null : validateDateFormat(params.get("date")));
        requestDTO.setOrder(params.get("order") == null || params.get("order").equals("") ? OrderType.DEFAULT : Integer.parseInt(params.get("order")));
        //Call to service
        return service.findPart(requestDTO);
    }

    /**
     * Updates only a data of a Part with a given DTO.
     * @param part DTO of a Part with the id and data to be updated.
     *          *  part.partCode Not Null or Empty.
     *          *  makerId should exist.
     *          *  discountId should exist.
     * @return ResponseEntity<String> OK HTTP code and message if update was successful
     * @throws InternalExceptionHandler if DTO is not correct
     */
    @PutMapping()
    @ApiOperation(
            value = "Update a part",
            nickname = "Update Part"
    )
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 402, message = "BAD REQUEST: \n" +
                                                "* no parameters were received to update. \n" +
                                                "* not found a provider with this id \n" +
                                                "* not found a discount rate with this id"),
            @ApiResponse(code = 404, message = "NOT FOUND: \n" +
                                                "* part with code xxxxxxxx not exist")

    })
    public ResponseEntity<String> updatePart(
            @ApiParam(value = "Information of the part to be update \n" +
                    "* partCode is required. \n" +
                    "* only the attributes sent as parameters will be modified. \n" +
                    "* makerId should exist. \n" +
                    "* discountId should exist.", required = true)
            @RequestBody PartDTO part) throws InternalExceptionHandler {
        service.updatePart(part);
        return ResponseEntity.status(HttpStatus.OK)
                .body("The part with partCode: " + part.getPartCode() + ", has been updated correctly.");
    }

    /**
     * GET method to search orders, it receives a map with the following data
     * dealerNumber:  identification of dealer whose orders are to be looked for
     * deliveryStatus:  ["P","D","F","C"] -> Pending, Delayed, Finished, Cancelled
     * order: ["1","2"] -> orderDate ASC, orderDate DESC
     *
     * @param params map of parameters given by user
     * @return OrderResponseDTO that contains the list of found orders
     */
    @GetMapping("orders")
    @ApiOperation(
            value = "Returns a subsidiary with its order list",
            nickname = "Orders for subsidiary"
    )
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 402, message = "BAD REQUEST: \n" +
                    "* The order type of query does not exist. \n" +
                    "* Params given in the request are wrong, deliveryStatus not in [P, D, F, C] \n" +
                    "* the subsidiary not found. \n" +
                    "* Params given in the request are wrong, Empty parameters. \n" +
                    "* Params given in the request are wrong, dealerNumber is missing from request")

    })
    public SubsidiaryResponseDTO findSubsidiaryOrders(
            @ApiParam(value = "* dealerNumber: ”0001” or ”1” -> idSubsidiary \n" +
                    "* deliveryStatus::  [”P”,”D”,”F”,”C”] -> Pending, Delayed, Finished, Cancelled \n" +
                    "* order: [”0”,1”,”2”] -> idOrder default, orderDate ASC, orderDate DESC", required = true)
            @RequestParam Map<String, String> params) throws InternalExceptionHandler {

        //Validations
        ValidationController.isOrdersEndpointMapValid(params);
        //Setting values to OrderRequestDTO
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setDealerNumber(Long.parseLong(params.get("dealerNumber")));
        orderRequestDTO.setDeliveryStatus(params.get("deliveryStatus") == null ? null : params.get("deliveryStatus"));
        orderRequestDTO.setOrder(params.get("order") == null || params.get("order").equals("") ? OrderType.DEFAULT : Integer.parseInt(params.get("order")));

        return warehouseService.findSubsidiaryOrders(orderRequestDTO);
    }

    /**
     * Gets a DTO of an order searched by a String orderNumberCM
     * orderNumberCM -> following the next model of String =  "0001-00000001"
     * "0001" = subsidiary Id
     * "00000001" = Order Id
     *
     * @param orderNumberCM model of String =  "0001-00000001"
     * @return OrderDTO DTO of an Order with the orderNumberCM
     * @throws InternalExceptionHandler if received orderNumberCM is misspelled or is not found
     */
    @GetMapping("orders/{orderNumberCM}")
    @ApiOperation(
            value = "Return a Order based on orderNumberCM",
            nickname = "Find Order by orderNumberCM"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponseDTO.class),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDTO.class)

    })
    public OrderResponseDTO findByOrderNumberCM(
            @ApiParam(value = "orderNumberCM -> following the next model of String =  \"0001-00000001\"\n" +
                    "     * \"0001\" = subsidiary Id  \n" +
                    "     * \"00000001\" = Order Id")
            @PathVariable("orderNumberCM") @Pattern(regexp = "^\\d{4}-\\d{8}$") String orderNumberCM) throws InternalExceptionHandler {

        if (!orderNumberCM.matches("^\\d{4}-\\d{8}$")) {
            throw new QueryException("pattern error");
        }
        return warehouseService.findByOrderNumberCM(orderNumberCM);
    }

    @GetMapping("stocks")
    public SubsidiaryStockResponseDTO findSubsidiaryStock(@RequestParam Map<String, String> params) throws InternalExceptionHandler {

        ValidationController.validateSubsidiaryStockParams(params);
        SubsidiaryStockRequestDTO request = new SubsidiaryStockRequestDTO();
        request.setDealerNumber(Long.parseLong(params.get("dealerNumber")));

        return warehouseService.findSubsidiaryStock(request);
    }

    /**
     * Create a Part with a given DTO.
     * @param part DTO of a Part with the partCode and data to be created.
     *          *  all atributes are required.
     *          *  makerId should exist.
     *          *  discountId should exist.
     * @return The Part created.
     * @throws PartAlreadyExistException If exist a Part with this partCode.
     * @throws DiscountRateIDNotFoundException If not exist a DiscountRate with this discountId.
     * @throws ProviderIdNotFoundException If exist a Provider with this makerId.
     */
    @PostMapping("")
    @ApiOperation(
            value = "Create a new part",
            nickname = "Create Part"
    )
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 402, message = "BAD REQUEST: \n" +
                    "* the part code xxxxxxxx already exist. \n" +
                    "* not found a provider with this id. \n" +
                    "* not found a discount rate with this id. \n")

    })
    public ResponseEntity<?> newPart(
            @ApiParam(value = "Information of the part to be crated", required = true)
            @Valid @RequestBody PartDTO part) throws Exception {

        if (part != null) {
            service.newPart(part);
        }
        return ResponseEntity.status(HttpStatus.OK)
                //.body("The part was crated successfully ");
                .body(part);
    }

    /**
     * Create a Order with a given DTO.
     * @param order DTO of a Order with the data to be created.
     *          * all atributes are required.
     *          * order.orderDetails.partCode should exist.
     *          * order.orderDetails.quantity It must be less than the stock that the part has.
     * @return The Order created
     * @throws PartNotExistException If not exist the Part with this partCode.
     * @throws NotEnoughStock If the quantity of OrderDetails is more than stock that the part has.
     * @throws InvalidAccountTypeExtensionException If accountType lenght is different than 1.
     */
    @PostMapping("orders")
    @ApiOperation(
            value = "Create a new order",
            nickname = "Create Order"
    )
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 402, message = "BAD REQUEST: \n" +
                    "* part  with partCode xxxxxxxx not exist.\n" +
                    "* not enough stock for partCode: xxxxxxxx. \n" +
                    "* the account type extension is invalid. \n")
    })
    public ResponseEntity<?> newOrder(
            @ApiParam(value = "Information of the order to be crated", required = true)
            @RequestBody OrderDTO order) throws Exception
    {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if(order != null)
        {
            warehouseService.newOrder(order,user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    /**
     * Update the OrderStatus from the parameter
     * @param orderNumberCM "0001-00000003" -> "0001" is idSubsidiary - "00000003" is idOrder
     * @param orderStatus ["P","D","F","C"] -> Pending, Delayed, Finished, Cancelled
     * @return ResponseEntity<String> OK HTTP code and message if update was successful
     * @throws IncorrectParamsGivenException order status does not exist.
     * @throws SubsidiaryNotFoundException IF Not Found Subsidiary with idSubsidiary.
     * @throws OrderIdNotFoundException IF Not Found Order with idSubsidiary and idOrder.
     * @throws OrderDeliveryStatusIsconcludedException OrderDeliveryStatus must be Pending "P" or Delayed "D".
     */
    @PutMapping("order/{orderNumberCM}/{orderStatus}")
    @ApiOperation(
            value = "Update a status order",
            nickname = "Update Status Order"
    )
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 402, message = "BAD REQUEST: \n" +
                    "* the subsidiary not found.\n" +
                    "* not found a order with this id \n" +
                    "* the order status has already concluded \n")
    })
    public ResponseEntity<?> updateOrderStatus(
            @ApiParam(value = "\"0001-00000003\" -> \"0001\" is idSubsidiary - \"00000003\" is idOrder", required = true)
            @PathVariable("orderNumberCM") @Pattern(regexp = "^\\d{4}-\\d{8}$") String orderNumberCM,
            @ApiParam(value = "[\"P\",\"D\",\"F\",\"C\"] -> Pending, Delayed, Finished, Cancelled", required = true)
            @PathVariable String orderStatus) throws InternalExceptionHandler {
        if (!orderNumberCM.matches("^\\d{4}-\\d{8}$")) {
            throw new QueryException("pattern error");
        }

        ValidationController.validateOrderStatus(orderStatus);

        warehouseService.changeDeliveryStatus(orderNumberCM, orderStatus);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
    }
}