package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidAccountTypeExtensionException extends InternalExceptionHandler{


    public InvalidAccountTypeExtensionException() {
        super("The account type extension is invalid", HttpStatus.NOT_FOUND);
    }
}

