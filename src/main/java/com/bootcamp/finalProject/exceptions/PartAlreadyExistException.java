package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class PartAlreadyExistException extends InternalExceptionHandler{


    public PartAlreadyExistException(Integer partCode) {
        super("The part code" + partCode + "already exist", HttpStatus.NOT_FOUND);
    }
}

