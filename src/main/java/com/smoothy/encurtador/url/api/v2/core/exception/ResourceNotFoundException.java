package com.smoothy.encurtador.url.api.v2.core.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

    public String getMessage(String message) {
        return getMessage();
    }

    public HttpStatus status(HttpStatus status) {
        return status(status);
    }
}
