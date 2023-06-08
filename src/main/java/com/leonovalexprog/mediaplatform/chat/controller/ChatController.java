package com.leonovalexprog.mediaplatform.chat.controller;

import com.leonovalexprog.mediaplatform.chat.dto.ChatResponseDto;
import com.leonovalexprog.mediaplatform.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/chat")
    public ChatResponseDto startNewChat(@RequestParam(required = true, value = "user_id") int user,
                                        @RequestHeader(value = "Authorization") String token) {
        return chatService.startNewChat(token, user);
    }

    @DeleteMapping("/chat")
    public void deleteChat(@RequestParam(required = true, value = "user_id") int user,
                           @RequestHeader(value = "Authorization") String token) {
        chatService.deleteChat(token, user);
    }
}
