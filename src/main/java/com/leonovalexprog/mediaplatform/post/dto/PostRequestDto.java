package com.leonovalexprog.mediaplatform.post.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class PostRequestDto {
    @NonNull
    private String header;
    @NonNull
    private String text;
}
