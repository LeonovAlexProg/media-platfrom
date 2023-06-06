package com.leonovalexprog.mediaplatform.post.repository;

import com.leonovalexprog.mediaplatform.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
