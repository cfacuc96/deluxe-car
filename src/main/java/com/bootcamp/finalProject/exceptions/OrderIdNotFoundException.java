package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class OrderIdNotFoundException extends InternalExceptionHandler{
    public OrderIdNotFoundException() {
        super("not found a order with this id", HttpStatus.NOT_FOUND);
    }
}
