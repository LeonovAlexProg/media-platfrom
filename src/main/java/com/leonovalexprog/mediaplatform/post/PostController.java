package com.leonovalexprog.mediaplatform.post;

import com.leonovalexprog.mediaplatform.exception.CustomErrorResponse;
import com.leonovalexprog.mediaplatform.post.dto.PostRequestDto;
import com.leonovalexprog.mediaplatform.post.dto.PostResponseDto;
import com.leonovalexprog.mediaplatform.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = { @Content(mediaType = "application/json")})
    })
    @PostMapping(produces = {"application/json", "image/jpg"})
    public PostResponseDto postNewPost(@RequestHeader(value = "Authorization") String token,
                                       @RequestPart() MultipartFile image,
                                       @Valid @RequestPart() PostRequestDto post) throws IOException {
        return postService.postNewPost(token, post, image);
    }

    @Operation(summary = "Get user posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got user posts",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @GetMapping("/{userId}")
    public List<PostResponseDto> getUserPosts(@PathVariable() int userId,
                                              @RequestParam(required = false, defaultValue = "10") int size,
                                              @RequestParam(required = false, defaultValue = "0") int page) {
        return postService.getUserPosts(userId, size, page);
    }

    @Operation(summary = "Update post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "403", description = "Access restricted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @PutMapping
    public PostResponseDto updatePost(@RequestPart(required = false) MultipartFile image,
                                      @Valid @RequestPart(required = false) PostRequestDto post,
                                      @RequestHeader(value = "Authorization") String token,
                                      @RequestParam(value = "post-id") int postId) throws IOException {
        return postService.updatePost(image, post, token, postId);
    }

    @Operation(summary = "Delete post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Post not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "403", description = "Access restricted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @DeleteMapping
    public void deletePost(@RequestHeader(value = "Authorization") String token,
                           @RequestParam(value = "post-id") int postId) {
        postService.deletePost(token, postId);
    }

    @Operation(summary = "Get user feed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got user feed posts",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not subscribed to anyone",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))})
    })
    @GetMapping("/feed")
    public List<PostResponseDto> getUserFeed(@RequestHeader(value = "Authorization") String token,
                                             @RequestParam(required = false, defaultValue = "10") int size,
                                             @RequestParam(required = false, defaultValue = "0") int page,
                                             @RequestParam(required = false, defaultValue = "desc") String order) {
        return postService.getUserFeed(token, size, page, order);
    }
}
