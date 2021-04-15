package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class DiscountRateIDNotFoundException extends InternalExceptionHandler{

    public DiscountRateIDNotFoundException() {
        super("not found a discount rate with this id", HttpStatus.NOT_FOUND);
    }
}
