package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class DeliveryStatusException extends InternalExceptionHandler{
    public DeliveryStatusException() {
        super("The delivery status does not exist.", HttpStatus.BAD_REQUEST);
    }
}
