package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class SubsidiaryNotFoundException extends InternalExceptionHandler{
    public SubsidiaryNotFoundException() {
        super("the subsidiary not found.", HttpStatus.BAD_REQUEST);
    }
}
