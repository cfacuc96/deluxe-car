package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.*;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import com.bootcamp.finalProject.utils.ValidationController;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

import static com.bootcamp.finalProject.utils.ValidationController.isListEndpointMapValid;
import static com.bootcamp.finalProject.utils.ValidationController.validateDateFormat;

@RestController
@RequestMapping("/api/v1/parts")
@Validated
public class PartController {

    @Autowired
    IPartService service;

    @Autowired
    IWarehouseService warehouseService;

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
        //TODO:Revisar con el grupo si existe una forma mejor de hacer esto o si esta correcto.
        if (params.get("order") == null || params.get("order").equals("")) {
            requestDTO.setOrder(0);
        } else {
            try {
                requestDTO.setOrder(Integer.parseInt(params.get("order")));
            } catch (NumberFormatException e) {
                throw new IncorrectParamsGivenException("the order might be a number.");
            }
        }
        //Call to service
        return service.findPart(requestDTO);
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
        if (params.get("order") == null || params.get("order").equals("")) {
            orderRequestDTO.setOrder(0);
        } else {
            try {
                orderRequestDTO.setOrder(Integer.parseInt(params.get("order")));
            } catch (NumberFormatException e) {
                throw new IncorrectParamsGivenException("the order might be a number.");
            }
        }
        return warehouseService.findSubsidiaryOrders(orderRequestDTO);
    }


    @GetMapping("orders/{orderNumberCM}")
    //Por algun motivo no se esta haciendo la validacion, esta el tag @validate en el controller tal como la documentacion
    //Había que cambiar el 3 por un 8.
    public OrderDTO findByOrderNumberCM(@PathVariable("orderNumberCM") @Pattern(regexp = "^\\d{4}-\\d{3}$") String orderNumberCM) throws InternalExceptionHandler {

        if (!orderNumberCM.matches("^\\d{4}-\\d{8}$")) {
            throw new QueryException("pattern error");
        }
        return warehouseService.findByOrderNumberCM(orderNumberCM);
    }


    @ExceptionHandler(InternalExceptionHandler.class)
    public ResponseEntity<ErrorDTO> handleException(InternalExceptionHandler e) {
        return new ResponseEntity<>(e.getError(), e.getReturnStatus());
    }
}
