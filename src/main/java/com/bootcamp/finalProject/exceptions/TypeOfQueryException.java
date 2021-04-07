package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class TypeOfQueryException extends InternalExceptionHandler
{
    public TypeOfQueryException()
    {
        super("The type of query does not exist.", HttpStatus.BAD_REQUEST);
    }
}
