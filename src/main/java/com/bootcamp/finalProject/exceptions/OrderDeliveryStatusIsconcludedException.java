package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class OrderDeliveryStatusIsconcludedException extends InternalExceptionHandler{
    public OrderDeliveryStatusIsconcludedException(String Status) {
        super("the order status has already concluded as "+Status, HttpStatus.BAD_REQUEST);
    }
}
