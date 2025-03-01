package com.smoothy.encurtador.url.api.v2.core.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String mensagem){
        super(mensagem);
    }
    public ConflictException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
