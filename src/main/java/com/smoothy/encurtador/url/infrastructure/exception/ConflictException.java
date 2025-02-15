package com.smoothy.encurtador.url.infrastructure.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String mensagem){
        super(mensagem);
    }
    public ConflictException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
