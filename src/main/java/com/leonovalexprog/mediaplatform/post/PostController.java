package com.leonovalexprog.mediaplatform.post;

import com.leonovalexprog.mediaplatform.post.dto.PostRequestDto;
import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import com.leonovalexprog.mediaplatform.exception.CustomErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.IOException;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(summary = "Post new post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posted successfully",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
    })
    @PostMapping(produces = {"application/json", "image/jpg"})
    public PostResponseDto postNewPost(@RequestHeader(value = "Authorization") String token,
                                       @RequestPart(required = true) MultipartFile image,
                                       @RequestPart(required = true) PostRequestDto post) throws IOException {
        return postService.postNewPost(token, post, image);
    }

    @GetMapping("/{userId}")
    public List<PostResponseDto> getUserPosts(@PathVariable(required = true) int userId,
                                              @RequestParam(required = false, defaultValue = "10") int size,
                                              @RequestParam(required = false, defaultValue = "0") int page) {
        return postService.getUserPosts(userId, size, page);
    }

    @PutMapping
    public PostResponseDto updatePost(@RequestPart(required = false) MultipartFile image,
                                      @RequestPart(required = false) PostRequestDto post,
                                      @RequestHeader(value = "Authorization") String token,
                                      @RequestParam(value = "post-id") int postId) throws IOException {
        return postService.updatePost(image, post, token, postId);
    }

    @DeleteMapping
    public void deletePost(@RequestHeader(value = "Authorization") String token,
                           @RequestParam(value = "post-id") int postId) {
        postService.deletePost(token, postId);
    }

    @GetMapping("/feed")
    public List<PostResponseDto> getUserFeed(@RequestHeader(value = "Authorization") String token,
                                             @RequestParam(required = false, defaultValue = "10") int size,
                                             @RequestParam(required = false, defaultValue = "0") int page,
                                             @RequestParam(required = false, defaultValue = "desc") String order) {
        return postService.getUserFeed(token, size, page, order);
    }
}
