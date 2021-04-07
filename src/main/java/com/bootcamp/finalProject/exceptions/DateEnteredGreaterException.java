package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class DateEnteredGreaterException extends InternalExceptionHandler
{
    public DateEnteredGreaterException()
    {
        super("The date entered is greater than the current date.", HttpStatus.BAD_REQUEST);
    }
}
