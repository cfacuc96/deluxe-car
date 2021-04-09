package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.ErrorDTO;
import com.bootcamp.finalProject.dtos.SubsidiaryResponseDTO;
import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.services.IOrderService;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.utils.ValidationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    IOrderService orderService;

    /**
     * GET method to search list of parts, it receives a map with the following data
     * queryType: [“C”,”P”,”V”] -> COMPLETE, PARTIAL, VARIATION
     * date:  date for de query consultation
     * order: [”0”,1”,”2”,”3”] -> orderDate default, orderDate ASC, orderDate DESC, orderDate LastChange
     * @param params map of parameters given by user
     * @return List<PartResponseDTO> that contains the list of parts that have had a change according to the query
     */


    @GetMapping("list")
    public List<PartResponseDTO> findPart(@Nullable @RequestParam Map<String, String> params) throws Exception {
        //Validations
        isListEndpointMapValid(params);
        //Set parameters for PartRequestDto
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setQueryType(params.get("queryType"));
        requestDTO.setDate((params.get("date") == null) ? null : validateDateFormat(params.get("date")));
        requestDTO.setOrder((params.get("order") == null) ? 0 : Integer.parseInt(params.get("order")));
        //Call to service service
        return service.findPart(requestDTO);
    }

    /**
     * GET method to search orders, it receives a map with the following data
     * dealerNumber:  identification of dealer whose orders are to be looked for
     * deliveryStatus:  ["P","D","F","C"] -> Pending, Delayed, Finished, Cancelled
     * order: ["1","2"] -> orderDate ASC, orderDate DESC
     * @param params map of parameters given by user
     * @return OrderResponseDTO that contains the list of found orders
     */
    @GetMapping("orders")
    public SubsidiaryResponseDTO findSubsidiaryOrders(@RequestParam Map<String, String> params) throws InternalExceptionHandler{
        //Validations
        ValidationController.isOrdersEndpointMapValid(params);
        //Setting values to OrderRequestDTO
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setDealerNumber(Long.parseLong(params.get("dealerNumber")));
        orderRequestDTO.setDeliveryStatus(params.get("deliveryStatus") ==null ? null : params.get("deliveryStatus"));
        orderRequestDTO.setOrder((params.get("order") == null) ? 0 : Integer.parseInt(params.get("order")));
        //
        return orderService.findSubsidiaryOrders(orderRequestDTO);
    }


    @ExceptionHandler(InternalExceptionHandler.class)
    public ResponseEntity<ErrorDTO> handleException(InternalExceptionHandler e) {
        return new ResponseEntity<>(e.getError(), e.getReturnStatus());
    }
}
