package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class ProviderIdNotFoundException extends InternalExceptionHandler
{
    public ProviderIdNotFoundException()
    {
        super("not found a provider with this id", HttpStatus.BAD_REQUEST);
    }
}
