package com.gnovoa.robot.exception;

public class IllegalOrientationStateException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public IllegalOrientationStateException() {
        super();
    }

    public IllegalOrientationStateException(final String message) {
        super(message);
    }

}
