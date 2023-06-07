package com.leonovalexprog.mediaplatform.post.mappers;

import com.leonovalexprog.mediaplatform.post.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageMapper {
    public static Image of(MultipartFile file) throws IOException {
        return Image.builder()
                .filename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .imageData(file.getBytes())
                .build();
    }
}
