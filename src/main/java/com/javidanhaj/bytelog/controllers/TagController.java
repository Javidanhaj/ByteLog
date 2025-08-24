package com.javidanhaj.bytelog.controllers;

import com.javidanhaj.bytelog.domain.dtos.CreateTagRequest;
import com.javidanhaj.bytelog.domain.dtos.TagResponse;
import com.javidanhaj.bytelog.domain.dtos.UpdateTagRequest;
import com.javidanhaj.bytelog.domain.entities.Tag;
import com.javidanhaj.bytelog.mappers.TagMapper;
import com.javidanhaj.bytelog.services.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tags", description = "Tag management operations")
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    @Operation(summary = "Get all tags", description = "Retrieve all tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tags",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream().map((tagMapper::toTagResponse)).toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    @Operation(summary = "Create tags", description = "Create new tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tags created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagRequest createTagRequest) {
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream().map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a tag", description = "Delete a tag by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tag deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "{id}")
    @Operation(summary = "Update a tag", description = "Update an existing tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Tag not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TagResponse> updateTag(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTagRequest updateTagRequest){
        Tag updatedTag = tagService.updateTag(id, updateTagRequest);
        TagResponse updatedTagResponse = tagMapper.toTagResponse(updatedTag);
        return ResponseEntity.ok(updatedTagResponse);
    }
}
