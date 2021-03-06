package com.ppiotr191.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not Found") //404
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3332292346834265371L;

    public NotFoundException(String msg){
        super( msg + " - Not Found");
    }
}
