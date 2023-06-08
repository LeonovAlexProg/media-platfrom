package com.leonovalexprog.mediaplatform.post.model;

import com.leonovalexprog.mediaplatform.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String header;
    private String text;
    @ManyToOne
    private Image image;
    @ManyToOne
    private User user;
}
