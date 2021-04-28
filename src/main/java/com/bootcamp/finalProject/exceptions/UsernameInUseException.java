package com.bootcamp.finalProject.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameInUseException extends InternalExceptionHandler {
    public UsernameInUseException() {
        super("Users are already loaded. Go login !", HttpStatus.BAD_REQUEST);
    }
}
