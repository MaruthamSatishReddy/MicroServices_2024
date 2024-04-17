package com.easyToBuy.Inventry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorObject> handleProductNotFoundException(ProductNotFoundException  foundException){
       return new ResponseEntity<>(ErrorObject.builder().errorMessage(foundException.getMessage()).errorCode(HttpStatus.NOT_FOUND.value()).build(), HttpStatus.NOT_FOUND);
    }
}
