package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class DiscountRateAlreadyExistException extends InternalExceptionHandler{
    public DiscountRateAlreadyExistException(Long idDiscountRate) {
        super("The discount with the id " + idDiscountRate + " already exist", HttpStatus.BAD_REQUEST);
    }
}
