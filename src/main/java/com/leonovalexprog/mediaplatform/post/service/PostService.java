package com.leonovalexprog.mediaplatform.post.service;

import com.leonovalexprog.mediaplatform.post.dto.PostRequestDto;
import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.mappers.PostMapper;
import com.leonovalexprog.mediaplatform.post.model.Image;
import com.leonovalexprog.mediaplatform.post.model.Post;
import com.leonovalexprog.mediaplatform.post.repository.PostRepository;
import com.leonovalexprog.mediaplatform.security.user.User;
import com.leonovalexprog.mediaplatform.security.user.UserRepository;
import com.leonovalexprog.mediaplatform.security.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ImageService imageService;

    public PostResponseDto postNewPost(Integer userId, PostRequestDto postDto, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User id %d not found", userId)));

        Image image = imageService.uploadImage(file);

        if (image == null)
            image = imageService.findImageByFilename(file.getOriginalFilename());

        Post post = Post.builder()
                .header(postDto.getHeader())
                .text(postDto.getText())
                .image(image)
                .user(user)
                .build();

        postRepository.save(post);

        byte[] imageData = imageService.downloadImage(image.getFilename());

        return PostMapper.toResponseDto(post, imageData);
    }
}
