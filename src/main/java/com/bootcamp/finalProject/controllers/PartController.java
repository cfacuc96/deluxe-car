package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.ErrorDTO;
import com.bootcamp.finalProject.dtos.OrderResponseDTO;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.services.PartService;
import com.bootcamp.finalProject.utils.ValidationController;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.bootcamp.finalProject.utils.ValidationController.validateDateFormat;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    @Autowired
    PartService service;

    @GetMapping("list")
    public List<PartResponseDTO> obtainList(@Nullable @RequestParam Map<String, String> params) throws Exception {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setQueryType(params.get("queryType"));
        requestDTO.setDate((params.get("date") == null) ? null : validateDateFormat(params.get("date")));
        requestDTO.setOrder((params.get("order") == null) ? 0 : Integer.parseInt(params.get("order")));

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
    public OrderResponseDTO ordersEndpoint(@RequestParam Map<String, String> params) throws InternalExceptionHandler{
        //Validations
        ValidationController.isOrdersEndpointMapValid(params);
        //TODO return service
        return null;
    }


    @ExceptionHandler(InternalExceptionHandler.class)
    public ResponseEntity<ErrorDTO> handleException(InternalExceptionHandler e) {
        return new ResponseEntity<>(e.getError(), e.getReturnStatus());
    }
}
