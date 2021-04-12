package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class ProviderIdNotFoundException extends InternalExceptionHandler
{
    public ProviderIdNotFoundException()
    {
        super("Id not found", HttpStatus.BAD_REQUEST);
    }
}
