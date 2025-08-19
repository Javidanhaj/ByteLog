package com.javidanhaj.bytelog.controllers;

import com.javidanhaj.bytelog.domain.dtos.TagResponse;
import com.javidanhaj.bytelog.domain.entities.Tag;
import com.javidanhaj.bytelog.mappers.TagMapper;
import com.javidanhaj.bytelog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
