package com.example.Projectwarehouse.errors;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee which id = " + id);
    }
}
