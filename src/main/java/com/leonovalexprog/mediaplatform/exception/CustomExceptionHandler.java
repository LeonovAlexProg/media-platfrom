package com.leonovalexprog.mediaplatform.exception;

import com.leonovalexprog.mediaplatform.security.user.exception.UserAuthenticationException;
import com.leonovalexprog.mediaplatform.security.user.exception.UserExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    protected ResponseEntity<Map<String, Object>> userExistsExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    protected ResponseEntity<Map<String, Object>> userValidationExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(errorInfo, HttpStatus.FORBIDDEN);
    }
}
