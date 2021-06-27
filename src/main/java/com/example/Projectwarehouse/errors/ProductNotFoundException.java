package com.example.Projectwarehouse.errors;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Could not find product which id = " + id);
    }
}
