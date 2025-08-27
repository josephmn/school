package com.api.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.api.school.exception.types.AlreadyExistsException;
import com.api.school.exception.types.NotFoundException;
import reactor.core.publisher.Mono;

/**
 * GlobalExceptionHandler class handles exceptions thrown by the application.
 * It provides custom error responses for different types of exceptions.
 * @author Joseph Magallanes
 * @since 2025-05-23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle NotFoundException and return a 404 response.
     *
     * @param exception the NotFoundException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ResponseEntity<ErrorResponse>>handleNotFoundException(NotFoundException exception) {
        final ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            exception.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }

    /**
     * Handle CustomerAlreadyExistsException and return a 409 response.
     *
     * @param exception the CustomerAlreadyExistsException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ResponseEntity<ErrorResponse>> handleNotFoundException(AlreadyExistsException exception) {
        final ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            exception.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(error));
    }
}
