package com.filmland.exception;

public class NoSuchCategoryException extends RuntimeException{
    public NoSuchCategoryException(String message) {
        super(message);
    }
}
