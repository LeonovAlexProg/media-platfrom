package com.leonovalexprog.mediaplatform.security.user.exception;

public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
