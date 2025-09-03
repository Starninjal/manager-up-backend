package com.example.employee_management_system.controller;

import com.example.employee_management_system.exception.AttributeNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestControllerHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AttributeNotFoundException.class)
    public String handleAttributeNotFoundException(AttributeNotFoundException e) {
        return e.getMessage();
    }
}
