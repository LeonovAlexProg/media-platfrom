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

    @PostMapping("/subscribe")
    public SubscriptionResponseDto subscribeToUser(@RequestParam(required = true, value = "user_id") int user,
                                                   @RequestHeader(value = "Authorization") String token) {
        return userService.subscribeUserTo(token, user);
    }

    @DeleteMapping("/unsubscribe")
    public void unsubscribeFromUser(@RequestParam(required = true, value = "user_id") int user,
                                                       @RequestHeader(value = "Authorization") String token) {
         userService.unsubscribeFromUser(token, user);
    }
}
