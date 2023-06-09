package com.leonovalexprog.mediaplatform.user.controller;

import com.leonovalexprog.mediaplatform.exception.CustomErrorResponse;
import com.leonovalexprog.mediaplatform.user.dto.SubscriptionResponseDto;
import com.leonovalexprog.mediaplatform.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Subscribe user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscribed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "User can not subscribe to himself",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "User already subscribed",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @PostMapping("/subscribe/{id}")
    public SubscriptionResponseDto subscribeToUser(@PathVariable() int id,
                                                   @RequestHeader(value = "Authorization") String token) {
        return userService.subscribeUserTo(token, id);
    }

    @Operation(summary = "Unsubscribe user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unsubscribed successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "User can not unsubscribe to himself",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "User not subscribed",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @DeleteMapping("/unsubscribe/{id}")
    public void unsubscribeFromUser(@PathVariable() int id,
                                    @RequestHeader(value = "Authorization") String token) {
         userService.unsubscribeFromUser(token, id);
    }


}
