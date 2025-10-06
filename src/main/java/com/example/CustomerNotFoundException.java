package com.example;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String name) {
        super(String.format("Customer {} not found", name));
    }
}
