package com.leonovalexprog.mediaplatform.chat.mappers;

import com.leonovalexprog.mediaplatform.chat.dto.ChatResponseDto;
import com.leonovalexprog.mediaplatform.chat.model.Chat;

public class ChatMapper {
    public static ChatResponseDto toResponseDto(Chat chat) {
        return ChatResponseDto.builder()
                .chatId(chat.getId())
                .firstUserId(chat.getFirstUser().getId())
                .secondUserId(chat.getSecondUser().getId())
                .build();
    }
}
