package com.smoothy.encurtador.url.api.v2.infra.handles;


import com.smoothy.encurtador.url.api.v2.core.exception.*;
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

@ExceptionHandler(NotNullException.class)
    public ResponseEntity<String> handleNotNullException(NotNullException nullException){
    return new ResponseEntity<>(nullException.getMessage("url cannot be null"), HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException exception){
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
}

}
