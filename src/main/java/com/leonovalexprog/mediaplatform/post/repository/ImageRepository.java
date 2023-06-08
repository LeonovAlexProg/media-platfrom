package com.leonovalexprog.mediaplatform.post.repository;

import com.leonovalexprog.mediaplatform.post.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findFirstByFilename(String filename);

    boolean existsByFilename(String filename);
}
