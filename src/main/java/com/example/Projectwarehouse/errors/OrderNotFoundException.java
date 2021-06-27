package com.example.Projectwarehouse.errors;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find order which id = " + id);
    }
}
