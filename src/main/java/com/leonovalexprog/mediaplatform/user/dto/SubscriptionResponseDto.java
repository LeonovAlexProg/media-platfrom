package com.leonovalexprog.mediaplatform.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponseDto {
    @NotNull
    private Integer subscriberId;
    @NotNull
    private Integer subscribedToID;
    @NotNull
    Boolean isApproved;
}
