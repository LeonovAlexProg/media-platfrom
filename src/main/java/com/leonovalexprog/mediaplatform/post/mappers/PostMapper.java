package com.leonovalexprog.mediaplatform.post.mappers;

import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostMapper {
    public static PostResponseDto toResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .header(post.getHeader())
                .text(post.getText())
                .image(post.getImage())
                .build();
    }

    public static List<PostResponseDto> listOf(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::toResponseDto)
                .toList();
    }
}
