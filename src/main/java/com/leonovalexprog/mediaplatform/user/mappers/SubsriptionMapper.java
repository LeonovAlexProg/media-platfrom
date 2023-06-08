package com.leonovalexprog.mediaplatform.user.mappers;

import com.leonovalexprog.mediaplatform.user.dto.SubscriptionResponseDto;
import com.leonovalexprog.mediaplatform.user.model.Subscription;

public class SubsriptionMapper {
    public static SubscriptionResponseDto toResponseDto(Subscription subscription) {
        return SubscriptionResponseDto.builder()
                .subscriberId(subscription.getUser().getId())
                .subscribedToID(subscription.getUserFriend().getId())
                .isApproved(subscription.isApproved())
                .build();
    }
}
