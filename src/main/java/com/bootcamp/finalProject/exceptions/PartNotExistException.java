package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class PartNotExistException extends InternalExceptionHandler{
    public PartNotExistException(Integer partCode) {
        super("Part code" +partCode + "not exist",HttpStatus.NOT_FOUND);
    }
}
