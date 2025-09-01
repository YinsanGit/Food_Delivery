package org.example.ytlearning.exception;

public class BadErrorRequestException extends RuntimeException {

    public BadErrorRequestException(String message) {
        super(message);
    }
}
