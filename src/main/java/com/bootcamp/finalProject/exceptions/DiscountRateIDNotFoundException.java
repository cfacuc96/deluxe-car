package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class DiscountRateIDNotFoundException extends InternalExceptionHandler{

    public DiscountRateIDNotFoundException() {
        super("Id not found", HttpStatus.NOT_FOUND);
    }
}
