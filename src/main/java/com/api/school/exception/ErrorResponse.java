package com.api.school.exception;

import java.time.LocalDateTime;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ErrorResponse class represents the structure of an error response.
 * It contains information about the HTTP status, error message, date of the error,
 * and a map of error codes and messages.
 * @author Joseph Magallanes
 * @since 2025-05-23
 */
@Data
public class ErrorResponse {
    private int status; // HTTP status code
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> errors;

    /**
     * Constructor for ErrorResponse with status, message, date, and errors.
     *
     * @param status HTTP status code
     * @param message Error message
     * @param errors Map of error codes and messages
     */
    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.date = LocalDateTime.now();
    }

    /**
     * Constructor for ErrorResponse with status, message, and date.
     *
     * @param status HTTP status code
     * @param message Error message
     */
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
