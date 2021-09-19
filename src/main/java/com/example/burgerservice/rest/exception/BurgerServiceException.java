package com.example.burgerservice.rest.exception;

public class BurgerServiceException extends RuntimeException {

    private String message;

    public BurgerServiceException(String message) {
        super(message);
        this.message = message;
    }
}
