package br.com.lovepet.exception.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(basePackages = "br.com.lovepet.controller")
public class GlobalControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleModuloNotFound(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleModuloNotFound(NoSuchElementException noSuchElementException){
        return new ResponseEntity<>(noSuchElementException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleModuloNotFound(AuthenticationException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }


}
