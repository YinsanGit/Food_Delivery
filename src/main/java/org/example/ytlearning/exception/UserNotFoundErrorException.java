package org.example.ytlearning.exception;



// Custom Exception Class
public class UserNotFoundErrorException extends RuntimeException {
    public UserNotFoundErrorException(String message) {
        super(message);
    }

    public UserNotFoundErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
