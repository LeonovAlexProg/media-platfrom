package com.leonovalexprog.mediaplatform.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponseDto {
    private int subscriberId;
    private int subscribedToID;
    boolean isApproved;
}
