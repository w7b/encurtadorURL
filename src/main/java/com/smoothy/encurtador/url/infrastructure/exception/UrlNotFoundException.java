package com.smoothy.encurtador.url.infrastructure.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message) {
        super(message);
    }
    public UrlNotFoundException(String message, Throwable throwable){ super(message, throwable);}
}
