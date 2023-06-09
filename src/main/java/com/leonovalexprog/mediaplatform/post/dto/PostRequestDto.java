package com.leonovalexprog.mediaplatform.post.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class PostRequestDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String header;
    @NotBlank
    @Size(min = 1, max = 255)
    private String text;
}
