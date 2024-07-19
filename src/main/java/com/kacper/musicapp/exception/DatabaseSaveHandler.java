package com.kacper.musicapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DatabaseSaveHandler
{
    @ExceptionHandler(DatabaseSaveException.class)
    public ResponseEntity<String> handleDatabaseSaveException(DatabaseSaveException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
