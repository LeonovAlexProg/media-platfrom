package com.leonovalexprog.mediaplatform.post.service;

import com.leonovalexprog.mediaplatform.post.dto.PostRequestDto;
import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.exception.AccessRestrictedException;
import com.leonovalexprog.mediaplatform.post.exception.PostNotFoundException;
import com.leonovalexprog.mediaplatform.post.mappers.PostMapper;
import com.leonovalexprog.mediaplatform.post.model.Image;
import com.leonovalexprog.mediaplatform.post.model.Post;
import com.leonovalexprog.mediaplatform.post.repository.PostRepository;
import com.leonovalexprog.mediaplatform.security.config.JwtService;
import com.leonovalexprog.mediaplatform.security.user.User;
import com.leonovalexprog.mediaplatform.security.user.UserRepository;
import com.leonovalexprog.mediaplatform.security.user.exception.UserNotFoundException;
import com.leonovalexprog.mediaplatform.post.utils.NullAwareBeanUtilsBean;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final ImageService imageService;

    @Transactional
    public PostResponseDto postNewPost(String token, PostRequestDto postDto, MultipartFile file) throws IOException {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User email %s not found", userEmail)));

        Image image = imageService.uploadImage(file);

        if (image == null) {
            image = imageService.findImageByFilename(file.getOriginalFilename());
        }

        Post post = Post.builder()
                .header(postDto.getHeader())
                .text(postDto.getText())
                .image(image)
                .user(user)
                .build();

        postRepository.save(post);

        return PostMapper.toResponseDto(post);
    }

    @Transactional
    public List<PostResponseDto> getUserPosts(String token, int size, int page) {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        if (userRepository.existsByEmail(userEmail)) {
            Pageable pageable =  PageRequest.of(page, size, Sort.by("id").ascending());

            List<Post> posts = postRepository.findAllByUserEmail(userEmail, pageable);

            return PostMapper.listOf(posts);
        }
        return Collections.emptyList();
    }

    @Transactional
    public PostResponseDto updatePost(MultipartFile image, PostRequestDto postDto, String token, int postId) throws IOException {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User email %s not found", userEmail)));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format("Post id %d not found", postId)));

        if (!user.equals(post.getUser()))
            throw new AccessRestrictedException(String.format("User id %d have no access to modify post id %d",
                    user.getId(),
                    post.getId()));

        NullAwareBeanUtilsBean.copyNonNullProperties(postDto, post);
        if (image != null) {
            Image newImage = imageService.uploadImage(image);
            post.setImage(newImage);
        }

        return PostMapper.toResponseDto(postRepository.save(post));
    }

    @Transactional
    public void deletePost(String token, int postId) {
        token = token.substring(7);
        String userEmail = jwtService.extractUserEmail(token);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format("User email %s not found", userEmail)));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format("Post id %d not found", postId)));

        if (!user.equals(post.getUser()))
            throw new AccessRestrictedException(String.format("User id %d have no access to modify post id %d",
                    user.getId(),
                    post.getId()));

        postRepository.delete(post);
    }
}
