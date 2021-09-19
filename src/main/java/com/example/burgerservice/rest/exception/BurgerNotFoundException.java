package com.example.burgerservice.rest.exception;

public class BurgerNotFoundException extends BurgerServiceException{

    public BurgerNotFoundException(String message) {
        super(message);
    }
}
