package com.example.departments.exception;

/**
 * Кастомное исключение.
 */
public class DeleteException extends RuntimeException {

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
