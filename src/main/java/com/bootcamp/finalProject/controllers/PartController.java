package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.mnemonics.OrderType;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import com.bootcamp.finalProject.utils.ValidationController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

import static com.bootcamp.finalProject.utils.ValidationController.isListEndpointMapValid;
import static com.bootcamp.finalProject.utils.ValidationController.validateDateFormat;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    @Autowired
    IPartService service;

    @Autowired
    IWarehouseService warehouseService;

    @Autowired
    PartRepository partRepository;

    /**
     * GET method to search list of parts, it receives a map with the following data
     * queryType: [“C”,”P”,”V”] -> COMPLETE, PARTIAL, VARIATION
     * date:  date for de query consultation
     * order: [”0”,1”,”2”,”3”] -> orderDate default, orderDate ASC, orderDate DESC, orderDate LastChange
     *
     * @param params map of parameters given by user
     * @return List<PartResponseDTO> that contains the list of parts that have had a change according to the query
     */
    @GetMapping("list")
    public List<PartResponseDTO> findPart(@Nullable @RequestParam Map<String, String> params) throws InternalExceptionHandler {
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

    @GetMapping("orders/{orderNumberCM}")
    //Por algun motivo no se esta haciendo la validacion, esta el tag @validate en el controller tal como la documentacion
    public OrderDTO findByOrderNumberCM(@PathVariable("orderNumberCM") @Pattern(regexp = "^\\d{4}-\\d{8}$") String orderNumberCM) throws InternalExceptionHandler {

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

    @PostMapping("providers/add")
    public void addProvider(@RequestBody ProviderDTO providerDTO) {
        service.saveProvider(providerDTO);
    }

    @GetMapping("providers/all")
    public List<ProviderDTO> findAllProviders() {
        return service.findAllProviders();
    }

    @GetMapping("providers/{id}")
    public Provider findProviderById(@PathVariable Long id) throws InternalExceptionHandler {
        return service.findProviderById(id);
    }


    @PostMapping("discountRates/add")
    public void addDiscountRate(@RequestBody DiscountRateDTO discountRateDTO) {
        service.saveDiscountRate(discountRateDTO);
    }

    @GetMapping("discountRates/all")
    public List<DiscountRateDTO> getALLDiscountRate() {
        return service.findALLDiscountRate();
    }

    //EndPoint de prueba para verificar la busqueda por id
    @GetMapping("discountRates/{id}")
    public DiscountRate findDiscountById(@PathVariable Long id) throws InternalExceptionHandler {
        return service.findDiscountRateById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> newPart(@Valid @RequestBody PartDTO part) throws Exception {

        if (part != null) {
            service.newPart(part);
        }
        return ResponseEntity.status(HttpStatus.OK)
                //.body("The part was crated successfully ");
                .body(part);
    }


    @PostMapping("orders")
    public ResponseEntity<?> newOrder(@Valid @RequestBody OrderDTO order) throws Exception 
    {
        if(order != null)
        {
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

        warehouseService.changeDeliveryStatus(orderNumberCM,orderStatus);
        return ResponseEntity.status(HttpStatus.OK).body("Order updated successfully");
    }


    @ExceptionHandler(InternalExceptionHandler.class)
    public ResponseEntity<ErrorDTO> handleException(InternalExceptionHandler e) {
        return new ResponseEntity<>(e.getError(), e.getReturnStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorDTO> handleInvalidFormatException(InvalidFormatException errorException) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Invalid Format Exception !");
        error.setDescription(errorException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleExceptionMethodArgument(MethodArgumentNotValidException errorException) {
        ErrorDTO error = new ErrorDTO();
        error.setName("Method Argument Not Valid Exception !");
        error.setDescription(errorException.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}