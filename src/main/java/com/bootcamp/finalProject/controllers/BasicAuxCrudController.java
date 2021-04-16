package com.bootcamp.finalProject.controllers;


import com.bootcamp.finalProject.dtos.DiscountRateDTO;
import com.bootcamp.finalProject.dtos.ProviderDTO;
import com.bootcamp.finalProject.exceptions.IncorrectParamsGivenException;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.mnemonics.ExceptionMessage;
import com.bootcamp.finalProject.model.DiscountRate;
import com.bootcamp.finalProject.model.Provider;
import com.bootcamp.finalProject.services.IPartService;
import com.bootcamp.finalProject.services.IWarehouseService;
import com.bootcamp.finalProject.utils.ValidationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parts")
public class BasicAuxCrudController extends CentralController{

    @Autowired
    IPartService service;

    @Autowired
    IWarehouseService warehouseService;

    /**
     * Creates a new provider into the database
     *
     * @param providerDTO
     */
    @PostMapping("providers")
    public ResponseEntity<?> addProvider(@Valid @RequestBody ProviderDTO providerDTO) throws InternalExceptionHandler {
        service.saveProvider(providerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerDTO.getName() + " has been added to the Database");
    }

    /**
     * Gets all the providers in the database
     *
     * @return List<ProviderDTO>
     */
    @GetMapping("providers")
    public List<ProviderDTO> findAllProviders() {
        return service.findAllProviders();
    }

    /**
     * Gets a provider searching by its Id in database
     *
     * @param id Long id
     * @return Provider
     * @throws InternalExceptionHandler
     */
    @GetMapping("providers/{id}")
    public ProviderDTO findProviderById(@PathVariable String id) throws InternalExceptionHandler {
        return service.findProviderById(ValidationController.validateProviderId(id));
    }


    /**
     * Creates a new discount rate into the database
     *
     * @param discountRateDTO DTO of the discountRate entity
     * @return ResponseEntity with the 201 CREATED code and a message if it was successful
     */
    @PostMapping("discountRates")
    public ResponseEntity<?> addDiscountRate(@Valid @RequestBody DiscountRateDTO discountRateDTO) throws InternalExceptionHandler {
        //validation that the attributes of the DTO are not null except for the id
        //ValidationController.validateDiscountRateDTOParams(discountRateDTO);
        //Call service to save the discountRate
        service.saveDiscountRate(discountRateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("A new discount rate has been added to the Database");
    }

    /**
     * Gets all discount rates in the database
     *
     * @return List<DiscountRateDTO>  A list of all discount rate
     */
    @GetMapping("discountRates")
    public List<DiscountRateDTO> getALLDiscountRate() {
        return service.findALLDiscountRate();
    }

    /**
     * Gets a Discount rate searching by its Id
     *
     * @param id id of the searched discount rate
     * @return DiscountRate entity of the discount rate
     * @throws InternalExceptionHandler if the given id is not found in the database
     */
    @GetMapping("discountRates/{id}")
    public DiscountRateDTO findDiscountById(@PathVariable Long id) throws InternalExceptionHandler {
        return service.findDiscountRateById(id);
    }
}
