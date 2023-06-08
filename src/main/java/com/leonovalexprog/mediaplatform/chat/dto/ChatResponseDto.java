package com.leonovalexprog.mediaplatform.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatResponseDto {
    private int chatId;

    private int firstUserId;

    private int secondUserId;
}
