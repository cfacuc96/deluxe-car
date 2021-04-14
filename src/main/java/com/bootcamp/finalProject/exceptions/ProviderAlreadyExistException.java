package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class ProviderAlreadyExistException extends InternalExceptionHandler{
    public ProviderAlreadyExistException(Long idProvider) {
        super("The provider with id " + idProvider + " already exist", HttpStatus.BAD_REQUEST);
    }
}
