package com.smoothy.encurtador.url.api.v2.core.exception;

import org.springframework.http.HttpStatus;

public class NotNullException extends RuntimeException {
    public NotNullException(String message) {
        super(message);
    }

    public String getMessage(String message) {
        return getMessage();
    }

    public HttpStatus status(HttpStatus status) {
        return status(status);
    }
}
