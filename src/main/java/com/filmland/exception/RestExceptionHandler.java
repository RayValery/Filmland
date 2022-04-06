package com.filmland.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({AlreadySubscribedCategoryException.class, InvalidLoginCredentialsException.class,
    NoSuchCategoryException.class, NoSuchUserException.class, NoSuchSubscriptionException.class, CustomerAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadySubscribedCategoryException(Exception ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
