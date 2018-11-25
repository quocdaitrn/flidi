package com.hcmut.advancedprogramming.flidi.exception;

public class MediaStorageException extends RuntimeException {
    public MediaStorageException(String message) {
        super(message);
    }

    public MediaStorageException(String message, Throwable e) {
        super(message, e);
    }
}
