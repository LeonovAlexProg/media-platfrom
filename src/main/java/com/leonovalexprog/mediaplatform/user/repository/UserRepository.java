package com.leonovalexprog.mediaplatform.user.repository;

import java.util.Optional;

import com.leonovalexprog.mediaplatform.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}