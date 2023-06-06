package com.leonovalexprog.mediaplatform.post;

import com.leonovalexprog.mediaplatform.post.dto.PostRequestDto;
import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping(value = "/post", produces = {"application/json", "image/jpg"})
    public PostResponseDto postNewPost(@RequestHeader(value = "X-User-id") Integer userId,
                                       @RequestPart MultipartFile image,
                                       @RequestPart PostRequestDto post) throws IOException {
        return postService.postNewPost(userId, post, image);
    }
}
