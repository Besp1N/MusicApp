package com.kacper.musicapp.exception;

public class DatabaseSaveException extends RuntimeException
{
    public DatabaseSaveException(String message) {
        super(message);
    }
}
