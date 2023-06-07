package com.leonovalexprog.mediaplatform.post.service;

import com.leonovalexprog.mediaplatform.post.exception.ImageNotFoundException;
import com.leonovalexprog.mediaplatform.post.mappers.ImageMapper;
import com.leonovalexprog.mediaplatform.post.model.Image;
import com.leonovalexprog.mediaplatform.post.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository repository;

    @Transactional
    public Image uploadImage(MultipartFile file) throws IOException {
        if (!this.isUploaded(file.getOriginalFilename())) {
            return repository.save(ImageMapper.of(file));
        } else
            return null;
    }

    @Transactional
    public Image findImageByFilename(String filename) {
        return repository.findFirstByFilename(filename)
                .orElseThrow(() -> new ImageNotFoundException(String.format("Image %s not found", filename)));
    }

    private boolean isUploaded(String filename) {
        return repository.existsByFilename(filename);
    }
}
