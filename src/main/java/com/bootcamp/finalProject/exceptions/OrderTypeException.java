package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class OrderTypeException extends InternalExceptionHandler
{
    public OrderTypeException()
    {
        super("The order type of query does not exist.", HttpStatus.BAD_REQUEST);
    }
}
