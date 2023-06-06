package com.leonovalexprog.mediaplatform.security.auth;

import com.leonovalexprog.mediaplatform.security.config.JwtService;
import com.leonovalexprog.mediaplatform.security.user.Role;
import com.leonovalexprog.mediaplatform.security.user.User;
import com.leonovalexprog.mediaplatform.security.user.UserRepository;
import com.leonovalexprog.mediaplatform.security.user.exception.UserAuthenticationException;
import com.leonovalexprog.mediaplatform.security.user.exception.UserExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException exc) {
            throw new UserExistsException("Почта/Имя пользователя или пароль уже заняты", exc.getCause());
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException exc) {
            throw new UserAuthenticationException(exc.getMessage(), exc.getCause());
        }
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}