package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectParamsGivenException extends InternalExceptionHandler
{
    public IncorrectParamsGivenException(String error)
    {
        super("Params given in the request are wrong, " + error, HttpStatus.BAD_REQUEST);
    }
}
