package com.bootcamp.finalProject.controllers;

import com.bootcamp.finalProject.dtos.ErrorDTO;
import com.bootcamp.finalProject.exceptions.InternalExceptionHandler;
import com.bootcamp.finalProject.dtos.PartRequestDTO;
import com.bootcamp.finalProject.dtos.PartResponseDTO;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.services.PartService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    @Autowired
    PartService service;

    @GetMapping("list")
    public List<PartResponseDTO> obtainList(@Nullable @RequestParam Map<String, String> params) throws Exception {
        PartRequestDTO requestDTO = new PartRequestDTO();
        requestDTO.setQueryType(params.get("querytype"));
        requestDTO.setDate(validateDates(params.get("date")));
        //requestDTO.setOrder(Integer.parseInt(params.get("order")));
        requestDTO.setOrder((params.get("order")==null)? 1: Integer.parseInt(params.get("order")));
        return service.findPart(requestDTO);
    }

    public Date validateDates(String date) throws Exception {
        Date newDate = null;
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            newDate = sdf.parse(date);
        } catch (Exception e) {
            //TODO:ADD EXCEPTION
            //Exception fecha valida
            //throw new InvalidateDateException(date);
        }
        return newDate;
    }

    @ExceptionHandler(InternalExceptionHandler.class)
    public ResponseEntity<ErrorDTO> handleException(InternalExceptionHandler e)
    {
        return new ResponseEntity<>(e.getError(), e.getReturnStatus());
    }
}