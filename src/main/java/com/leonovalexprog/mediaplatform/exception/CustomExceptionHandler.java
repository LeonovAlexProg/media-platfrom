package com.leonovalexprog.mediaplatform.exception;

import com.leonovalexprog.mediaplatform.chat.exception.ChatExistsException;
import com.leonovalexprog.mediaplatform.chat.exception.ChatNotFoundException;
import com.leonovalexprog.mediaplatform.chat.exception.UsersNotFriendsException;
import com.leonovalexprog.mediaplatform.post.exception.AccessRestrictedException;
import com.leonovalexprog.mediaplatform.post.exception.ImageNotFoundException;
import com.leonovalexprog.mediaplatform.post.exception.PostNotFoundException;
import com.leonovalexprog.mediaplatform.user.exception.*;
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

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Map<String, Object>> userNotFoundExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessRestrictedException.class)
    protected ResponseEntity<Map<String, Object>> accessRestrictedExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(errorInfo, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    protected ResponseEntity<Map<String, Object>> imageNotFoundExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<Map<String, Object>> postNotFoundExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubscriptionExistsException.class)
    protected ResponseEntity<Map<String, Object>> subscriptionExistsExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    protected ResponseEntity<Map<String, Object>> subscriptionNotFoundExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SameUserException.class)
    protected ResponseEntity<Map<String, Object>> sameUserExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChatExistsException.class)
    protected ResponseEntity<Map<String, Object>> chatExistsExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ChatNotFoundException.class)
    protected ResponseEntity<Map<String, Object>> chatNotFoundExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsersNotFriendsException.class)
    protected ResponseEntity<Map<String, Object>> usersNotFriendsExceptionHandler(Exception e) {
        log.debug(e.getMessage());
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("message", e.getMessage());
        errorInfo.put("status", HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }
}
