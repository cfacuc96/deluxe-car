package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class NotParamsToModifyException extends InternalExceptionHandler{
    public NotParamsToModifyException() {
        super("no parameters were received to update.", HttpStatus.BAD_REQUEST);
    }
}
