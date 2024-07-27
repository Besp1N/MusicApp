package com.kacper.musicapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoNAuthenticatedUserHandler
{
    @ExceptionHandler(NoNAuthenticatedUserException.class)
    public ResponseEntity<String> handleNoNAuthenticatedUserException(NoNAuthenticatedUserException e) {
        return new ResponseEntity<>(e.getMessage(), org.springframework.http.HttpStatus.UNAUTHORIZED);
    }
}
