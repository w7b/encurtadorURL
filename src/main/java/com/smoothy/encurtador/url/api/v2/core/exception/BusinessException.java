package com.smoothy.encurtador.url.api.v2.core.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message, NullPointerException urlCoreNãoPodeSerNulo) {
        super(message);
    }

}
