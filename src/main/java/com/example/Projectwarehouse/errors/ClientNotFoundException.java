package com.example.Projectwarehouse.errors;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Could not find client which id = " + id);
    }
}
