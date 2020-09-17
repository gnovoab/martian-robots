package com.gnovoa.robot.exception;

public class IllegalInstructionStateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalInstructionStateException(final String message) {
        super(message);
    }
}
