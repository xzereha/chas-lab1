package com.example.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String productName) {
        super(String.format("Product {} not found", productName));
    }

}
