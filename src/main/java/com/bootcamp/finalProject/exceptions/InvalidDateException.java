package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidDateException extends InternalExceptionHandler{
    public InvalidDateException() {
        super("the date format is not correct, please use the yyyy-mm-dd format", HttpStatus.BAD_REQUEST);
    }
}
