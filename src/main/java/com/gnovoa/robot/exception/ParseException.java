package com.gnovoa.robot.exception;

/**
 * Exception related processing objects.
 */
public class ParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     * @param cause
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}