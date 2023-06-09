package com.leonovalexprog.mediaplatform.user.controller;

import com.leonovalexprog.mediaplatform.user.dto.SubscriptionResponseDto;
import com.leonovalexprog.mediaplatform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/subscribe/{id}")
    public SubscriptionResponseDto subscribeToUser(@PathVariable(required = true) int id,
                                                   @RequestHeader(value = "Authorization") String token) {
        return userService.subscribeUserTo(token, id);
    }

    @DeleteMapping("/unsubscribe/{id}")
    public void unsubscribeFromUser(@PathVariable(required = true) int id,
                                                       @RequestHeader(value = "Authorization") String token) {
         userService.unsubscribeFromUser(token, id);
    }


}
