package com.leonovalexprog.mediaplatform.post.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filename;
    @Lob
    @Column(name = "image_data")
    private byte[] imageData = null;
    private String contentType = null;
}