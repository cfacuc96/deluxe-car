package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class LoginInvalidException extends InternalExceptionHandler
{
    public LoginInvalidException()
    {
        super("The user or password is invalid.", HttpStatus.BAD_REQUEST);
    }
}
