package com.kotuko.usermanagementsystem.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class KotukoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KotukoException.class)
    public ResponseEntity<?> handleException(KotukoException errorDetails) {
        return new ResponseEntity<>(new ExceptionResponse(errorDetails.getMessage(),errorDetails.getCode()),errorDetails.getCode());
    }
}
