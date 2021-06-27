package com.example.Projectwarehouse.errors;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException(Long id) {
        super("Could not find warehouse which id = " + id);
    }
}
