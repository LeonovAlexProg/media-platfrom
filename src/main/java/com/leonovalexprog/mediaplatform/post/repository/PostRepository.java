package com.leonovalexprog.mediaplatform.post.repository;

import com.leonovalexprog.mediaplatform.post.model.Post;
import com.leonovalexprog.mediaplatform.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUserEmail(String email, Pageable pageable);

    @Query(value = "select p from Post as p " +
            "where p.user IN (?1)")
    List<Post> findAllBySubscriptions(List<User> users, Pageable pageable);
}
