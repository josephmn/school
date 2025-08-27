package com.api.school.exception.types;

/**
 * Exception thrown when a customer already exists.
 * @author Joseph Magallanes
 * @since 2025-05-23
 */
public class AlreadyExistsException extends RuntimeException {

    /**
     * Constructor for CustomerAlreadyExistsException.
     *
     * @param message the detail message
     * @param args the arguments to format the message
     */
    public AlreadyExistsException(String message, Object... args) {
        super(String.format(message, args));
    }
}
