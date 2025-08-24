package com.javidanhaj.bytelog.controllers;

import com.javidanhaj.bytelog.domain.CreatePostRequest;
import com.javidanhaj.bytelog.domain.UpdatePostRequest;
import com.javidanhaj.bytelog.domain.dtos.CreatePostRequestDto;
import com.javidanhaj.bytelog.domain.dtos.PostDto;
import com.javidanhaj.bytelog.domain.entities.Post;
import com.javidanhaj.bytelog.domain.entities.User;
import com.javidanhaj.bytelog.mappers.PostMapper;
import com.javidanhaj.bytelog.services.PostService;
import com.javidanhaj.bytelog.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post management operations")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all published posts", description = "Retrieve all published posts with optional filtering by category or tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved posts",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PostDto>> getAllPosts(
            @Parameter(description = "Filter by category ID") @RequestParam(required = false) UUID categoryId,
            @Parameter(description = "Filter by tag ID") @RequestParam(required = false) UUID tagId) {

        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(postMapper::toDto).toList();

        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    @Operation(summary = "Get user's draft posts", description = "Retrieve all draft posts for the authenticated user")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved draft posts",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userID) {
        User loggedInUser = userService.getUserByID(userID);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();

        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    @Operation(summary = "Create a new post", description = "Create a new blog post")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userID) {
        User loggedInUser = userService.getUserByID(userID);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post createdPost = postService.createPost(loggedInUser, createPostRequest);
        PostDto createdPostDto = postMapper.toDto(createdPost);

        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update a post", description = "Update an existing blog post")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequest updatePostRequest) {
        Post updatedPost = postService.updatePost(id, updatePostRequest);
        PostDto updatedPostDto = postMapper.toDto(updatedPost);

        return ResponseEntity.ok(updatedPostDto);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get a post by ID", description = "Retrieve a specific blog post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved post",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDto.class))),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public ResponseEntity<PostDto> getPostById(@PathVariable UUID id) {
        Post post = postService.getPost(id);
        PostDto postDto = postMapper.toDto(post);

        return ResponseEntity.ok(postDto);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a post", description = "Delete a blog post by its ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
