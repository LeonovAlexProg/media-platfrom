package com.leonovalexprog.mediaplatform.user.exception;

public class SameUserException extends RuntimeException{
    public SameUserException(String message) {
        super(message);
    }
}
