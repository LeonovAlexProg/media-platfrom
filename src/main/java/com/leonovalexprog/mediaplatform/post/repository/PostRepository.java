package com.leonovalexprog.mediaplatform.post.repository;

import com.leonovalexprog.mediaplatform.post.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUserEmail(String email, Pageable pageable);
}
