package com.leonovalexprog.mediaplatform.post.mappers;

import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.model.Post;
import org.springframework.web.multipart.MultipartFile;

public class PostMapper {
    public static PostResponseDto toResponseDto(Post post, byte[] imageData) {
        return PostResponseDto.builder()
                .id(post.getId())
                .header(post.getHeader())
                .text(post.getText())
                .image(imageData)
                .build();
    }
}
