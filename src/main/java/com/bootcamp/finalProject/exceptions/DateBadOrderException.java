package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class DateBadOrderException extends InternalExceptionHandler{
    public DateBadOrderException() {
        super("dateFrom must be before dateTo", HttpStatus.BAD_REQUEST);
    }
}
