package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
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
     * Updates data of a Part with a given DTO
     *
     * @param part DTO of a Part with the id and data to be updated
     * @return ResponseEntity<String> OK HTTP code and message if update was successful
     * @throws InternalExceptionHandler if DTO is not correct
     */
    @PutMapping()
    public ResponseEntity<String> updatePart(@RequestBody PartDTO part) throws InternalExceptionHandler {
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
    public SubsidiaryResponseDTO findSubsidiaryOrders(@RequestParam Map<String, String> params) throws InternalExceptionHandler {
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

    }
    )
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

    @PostMapping("")
    @ApiOperation(
            value = "Create a new part",
            nickname = "Create Part"
    )
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

    @PostMapping("orders")
    public ResponseEntity<?> newOrder(@Valid @RequestBody OrderDTO order) throws Exception {
        if (order != null) {
            warehouseService.newOrder(order);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PutMapping("order/{orderNumberCM}/{orderStatus}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("orderNumberCM") @Pattern(regexp = "^\\d{4}-\\d{8}$") String orderNumberCM,
                                               @PathVariable String orderStatus) throws InternalExceptionHandler {
        if (!orderNumberCM.matches("^\\d{4}-\\d{8}$")) {
            throw new QueryException("pattern error");
        }

        ValidationController.validateOrderStatus(orderStatus);

        warehouseService.changeDeliveryStatus(orderNumberCM, orderStatus);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
    }
}