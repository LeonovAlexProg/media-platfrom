package com.leonovalexprog.mediaplatform.post.exception;

public class AccessRestrictedException extends RuntimeException {
    public AccessRestrictedException(String message) {
        super(message);
    }
}
