package com.leonovalexprog.mediaplatform.security.user.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
