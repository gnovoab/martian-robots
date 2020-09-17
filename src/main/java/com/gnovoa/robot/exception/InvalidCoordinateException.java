package com.gnovoa.robot.exception;

public class InvalidCoordinateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidCoordinateException(final String message) {
        super(message);
    }
}
