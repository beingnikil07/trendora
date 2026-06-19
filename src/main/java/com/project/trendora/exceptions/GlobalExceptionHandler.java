package com.project.trendora.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExists ex){
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExist.class)
    public ResponseEntity<?> handleProductAlreadyExists(ProductAlreadyExist ex){
        return ResponseEntity.ok(ex.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public  ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex){
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleGlobalRuntimeException(RuntimeException ex){
        return ResponseEntity.ok(ex.getMessage());
    }
}

