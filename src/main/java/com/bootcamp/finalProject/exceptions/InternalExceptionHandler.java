package com.bootcamp.finalProject.exceptions;

import com.bootcamp.finalProject.dtos.ErrorDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class InternalExceptionHandler extends Exception
{
    private ErrorDTO error;
    private HttpStatus returnStatus;

    public InternalExceptionHandler(String message, HttpStatus status)
    {
        this.error = new ErrorDTO();
        error.setName(status.toString());
        error.setDescription(message);
        this.setReturnStatus(status);
    }
}
