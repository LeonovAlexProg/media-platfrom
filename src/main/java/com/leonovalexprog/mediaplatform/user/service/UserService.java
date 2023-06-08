package com.leonovalexprog.mediaplatform.user.service;

import com.leonovalexprog.mediaplatform.security.config.JwtService;
import com.leonovalexprog.mediaplatform.user.dto.SubscriptionResponseDto;
import com.leonovalexprog.mediaplatform.user.exception.*;
import com.leonovalexprog.mediaplatform.user.mappers.SubsriptionMapper;
import com.leonovalexprog.mediaplatform.user.model.Subscription;
import com.leonovalexprog.mediaplatform.user.model.User;
import com.leonovalexprog.mediaplatform.user.repository.SubscriptionRepository;
import com.leonovalexprog.mediaplatform.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final JwtService jwtService;

    @Transactional
    public SubscriptionResponseDto subscribeUserTo(String token, int userId) {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", userEmail)));
        User friend = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with with id %d not found", userId)));

        if (user.equals(friend)) {
            throw new SameUserException("User can not subscribe to himself");
        }

        if (subscriptionRepository.existsByUserAndFriend(user, friend)) {
            throw new SubscriptionExistsException("Already subscribed");
        } else if (subscriptionRepository.existsByUserAndFriend(friend, user)) {
            return acceptFriendship(user, friend);
        }

        Subscription subscription = Subscription.builder()
                .user(user)
                .userFriend(friend)
                .isApproved(false)
                .build();

        subscriptionRepository.save(subscription);

        return SubsriptionMapper.toResponseDto(subscription);
    }

    @Transactional
    public void unsubscribeFromUser(String token, int userId) {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", userEmail)));
        User friend = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with with id %d not found", userId)));

        if (user.equals(friend)) {
            throw new SameUserException("User can not unsubscribe from himself");
        }

        if (!subscriptionRepository.existsByUserAndFriend(user, friend)) {
            throw new SubscriptionNotFoundException("User not subscribed");
        }

        Subscription subscription = subscriptionRepository.findByUserAndUserFriend(user, friend);
        subscriptionRepository.deleteById(subscription.getId());

        if (subscriptionRepository.existsByUserAndFriend(friend, user)) {
            subscription = subscriptionRepository.findByUserAndUserFriend(friend, user);
            subscription.setApproved(false);
            subscriptionRepository.save(subscription);
        }
    }

    private SubscriptionResponseDto acceptFriendship(User user, User friend) {
        Subscription subscription = subscriptionRepository.findByUserAndUserFriend(friend, user);

        subscription.setApproved(true);

        subscriptionRepository.save(subscription);

        Subscription newSubscription = Subscription.builder()
                .user(user)
                .userFriend(friend)
                .isApproved(true)
                .build();

        subscriptionRepository.save(newSubscription);


        return SubsriptionMapper.toResponseDto(newSubscription);
    }
}
