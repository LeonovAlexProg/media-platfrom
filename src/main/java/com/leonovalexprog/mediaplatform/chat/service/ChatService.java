package com.leonovalexprog.mediaplatform.chat.service;

import com.leonovalexprog.mediaplatform.chat.dto.ChatResponseDto;
import com.leonovalexprog.mediaplatform.chat.exception.ChatExistsException;
import com.leonovalexprog.mediaplatform.chat.exception.ChatNotFoundException;
import com.leonovalexprog.mediaplatform.chat.exception.UsersNotFriendsException;
import com.leonovalexprog.mediaplatform.chat.mappers.ChatMapper;
import com.leonovalexprog.mediaplatform.chat.model.Chat;
import com.leonovalexprog.mediaplatform.chat.repository.ChatRepository;
import com.leonovalexprog.mediaplatform.security.config.JwtService;
import com.leonovalexprog.mediaplatform.user.exception.UserNotFoundException;
import com.leonovalexprog.mediaplatform.user.model.Subscription;
import com.leonovalexprog.mediaplatform.user.model.User;
import com.leonovalexprog.mediaplatform.user.repository.SubscriptionRepository;
import com.leonovalexprog.mediaplatform.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final JwtService jwtService;

    @Transactional
    public ChatResponseDto startNewChat(String token, int userId) {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", userEmail)));
        User friend = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with with id %d not found", userId)));

        if (chatRepository.existsByUsers(user, friend)) {
            throw new ChatExistsException("Chat already exists");
        }

        if (subscriptionRepository.areFriends(user, friend)) {
            Chat newChat = Chat.builder()
                    .firstUser(user)
                    .secondUser(friend)
                    .build();

            chatRepository.save(newChat);

            return ChatMapper.toResponseDto(newChat);
        } else {
            throw new UsersNotFriendsException("Users are not friends");
        }
    }

    @Transactional
    public void deleteChat(String token, int userId) {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", userEmail)));
        User friend = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with with id %d not found", userId)));

        if (!chatRepository.existsByUsers(user, friend)) {
            throw new ChatNotFoundException("Chat does not exist");
        }

        Chat chat = chatRepository.findByUsers(user, friend);

        chatRepository.delete(chat);
    }
}
