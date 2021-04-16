package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameInUseException extends InternalExceptionHandler {

    public UsernameInUseException(String message) {
        super("Username: " + message +"alredy in use",HttpStatus.BAD_REQUEST );
    }
}
