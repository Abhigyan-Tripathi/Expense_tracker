package com.Abhigyan.Expensetracker.exception;

import java.time.LocalDateTime;

// The consistent JSON shape every error response will follow, e.g.:
// { "status": 404, "message": "Expense not found with id: 5", "timestamp": "..." }
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
