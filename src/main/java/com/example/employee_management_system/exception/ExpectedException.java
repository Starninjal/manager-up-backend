package com.example.employee_management_system.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExpectedException extends RuntimeException {
    private String message;
    private Object[] params;
    public ExpectedException(String message, Object... params) {
        this.message = message;
        this.params = params;
    }
}
