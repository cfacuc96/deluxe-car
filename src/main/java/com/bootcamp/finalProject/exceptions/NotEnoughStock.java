package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class NotEnoughStock extends InternalExceptionHandler {
    public NotEnoughStock(String partCode) {
        super("Not enough stock for partCode: " + partCode, HttpStatus.BAD_REQUEST);
    }
}
