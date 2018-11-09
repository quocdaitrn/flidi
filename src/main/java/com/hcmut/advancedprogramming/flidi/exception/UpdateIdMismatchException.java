package com.hcmut.advancedprogramming.flidi.exception;

public class UpdateIdMismatchException extends RuntimeException {

    public UpdateIdMismatchException() {
        super();
    }

    public UpdateIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UpdateIdMismatchException(final String message) {
        super(message);
    }

    public UpdateIdMismatchException(final Throwable cause) {
        super(cause);
    }
}
