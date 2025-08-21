package com.javidanhaj.bytelog.controllers;

import com.javidanhaj.bytelog.domain.dtos.CreateTagRequest;
import com.javidanhaj.bytelog.domain.dtos.TagResponse;
import com.javidanhaj.bytelog.domain.entities.Tag;
import com.javidanhaj.bytelog.mappers.TagMapper;
import com.javidanhaj.bytelog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream().map((tagMapper::toTagResponse)).toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagRequest createTagRequest){
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream().map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
