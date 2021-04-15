package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class PartNotExistException extends InternalExceptionHandler{
    public PartNotExistException(Integer partCode) {
        super("part  with partCode " +partCode + " not exist",HttpStatus.NOT_FOUND);
    }
}
