package com.Abhigyan.Expensetracker.exception;

// A plain custom exception - extends RuntimeException so we don't have to
// declare "throws" everywhere it might be thrown. The Service layer throws
// this when, e.g., someone requests an Expense id that doesn't exist. The
// GlobalExceptionHandler below catches it and converts it into a clean
// 404 JSON response instead of a raw stack trace.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
