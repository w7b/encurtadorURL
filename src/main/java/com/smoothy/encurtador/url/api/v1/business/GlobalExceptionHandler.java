package com.smoothy.encurtador.url.api.v1.business;

import com.smoothy.encurtador.url.infrastructure.exception.ConflictException;
import com.smoothy.encurtador.url.infrastructure.exception.ResourceNotFoundException;
import com.smoothy.encurtador.url.infrastructure.exception.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
}

@ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
}

@ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handleUrlNotFoundException(UrlNotFoundException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
}

}
