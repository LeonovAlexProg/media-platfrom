package com.leonovalexprog.mediaplatform.chat.controller;

import com.leonovalexprog.mediaplatform.chat.dto.ChatResponseDto;
import com.leonovalexprog.mediaplatform.chat.service.ChatService;
import com.leonovalexprog.mediaplatform.exception.CustomErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {
    private final ChatService chatService;

    @Operation(summary = "Start new chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chat started successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ChatResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Users are not friends",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Chat already exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @PostMapping("/chat")
    public ChatResponseDto startNewChat(@RequestParam(value = "user_id") int user,
                                        @RequestHeader(value = "Authorization") String token) {
        return chatService.startNewChat(token, user);
    }

    @Operation(summary = "Delete chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chat deleted successfully",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Chat not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @DeleteMapping("/chat")
    public void deleteChat(@RequestParam(value = "user_id") int user,
                           @RequestHeader(value = "Authorization") String token) {
        chatService.deleteChat(token, user);
    }
}
