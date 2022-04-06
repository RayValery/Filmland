package com.filmland.exception;

public class NoSuchSubscriptionException extends RuntimeException{
    public NoSuchSubscriptionException(String message) {
        super(message);
    }
}
