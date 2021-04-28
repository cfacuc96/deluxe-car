package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class ThereIsAlredyStockException extends InternalExceptionHandler{
    public ThereIsAlredyStockException(Integer partCode) {
        super("there is already stock for partCode: " + partCode, HttpStatus.BAD_REQUEST);
    }
}
