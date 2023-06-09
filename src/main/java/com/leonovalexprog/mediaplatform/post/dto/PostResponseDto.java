package com.leonovalexprog.mediaplatform.post.dto;

import com.leonovalexprog.mediaplatform.post.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostResponseDto {
    private Integer id;
    private String header;
    private String text;
    private Image image;
}
