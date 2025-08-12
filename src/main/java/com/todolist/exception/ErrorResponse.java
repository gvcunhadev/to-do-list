package com.todolist.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String message,
        List<String> details,
        LocalDateTime timestamp
) {
    public ErrorResponse(HttpStatus status, String message, List<String> details) {
        this(status.value(), status.getReasonPhrase(), message, details, LocalDateTime.now());
    }
}
