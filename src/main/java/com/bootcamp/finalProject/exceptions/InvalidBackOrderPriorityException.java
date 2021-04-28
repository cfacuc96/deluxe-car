package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidBackOrderPriorityException extends InternalExceptionHandler{
    public InvalidBackOrderPriorityException() {
        super("The priority is invalid. The priority must be 'H' 'N' 'L' ", HttpStatus.BAD_REQUEST);
    }
}
