package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class OrderIdNotFoundException extends InternalExceptionHandler{
    public OrderIdNotFoundException() {
        super("Id not found", HttpStatus.NOT_FOUND);
    }
}
