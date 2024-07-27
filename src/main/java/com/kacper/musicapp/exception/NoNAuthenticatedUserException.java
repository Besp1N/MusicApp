package com.kacper.musicapp.exception;

public class NoNAuthenticatedUserException extends RuntimeException
{
    public NoNAuthenticatedUserException(String message) {
        super(message);
    }
}
