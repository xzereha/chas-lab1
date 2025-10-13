package com.example.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String name) {
        super(String.format("Customer %s not found", name));
    }
}
