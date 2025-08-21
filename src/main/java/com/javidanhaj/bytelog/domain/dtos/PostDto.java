package com.javidanhaj.bytelog.domain.dtos;

import com.javidanhaj.bytelog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PostDto {

    private UUID id;
    private String title;
    private String content;
    private AuthorDto author;
    private CategoryDto category;
    private Set<TagResponse> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PostStatus postStatus;

}
