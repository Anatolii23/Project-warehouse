package com.example.Projectwarehouse.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandling {
    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String serviceNotFoundException(ClientNotFoundException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String serviceNotFoundException(EmployeeNotFoundException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String serviceNotFoundException(OrderNotFoundException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String serviceNotFoundException(ProductNotFoundException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(WarehouseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String serviceNotFoundException(WarehouseNotFoundException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }
}
