package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.ErrorDTO;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    @Autowired
    PartService service;

    //TODO: ARMAR el o los ENDPOINT.


    @ExceptionHandler(InternalExceptionHandler.class)
    public ResponseEntity<ErrorDTO> handleException(InternalExceptionHandler e)
    {
        return new ResponseEntity<>(e.getError(), e.getReturnStatus());
    }
}
