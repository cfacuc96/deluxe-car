package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends InternalExceptionHandler {
    public InvalidTokenException() {
        super("InvalidToken", HttpStatus.BAD_REQUEST);
    }
}
