package com.example.employee_management_system.exception;

public class AttributeNotFoundException extends ExpectedException {
    public AttributeNotFoundException(String message, Object... params) {
        super(message, params);
    }
}
