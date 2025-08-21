package com.javidanhaj.bytelog.services;

import com.javidanhaj.bytelog.domain.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPosts(UUID categoryId, UUID tagId);
}
