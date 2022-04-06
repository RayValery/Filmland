package com.filmland.exception;

public class AlreadySubscribedCategoryException extends RuntimeException{
    public AlreadySubscribedCategoryException(String message) {
        super(message);
    }
}
