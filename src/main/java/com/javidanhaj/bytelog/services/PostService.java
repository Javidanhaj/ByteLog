package com.javidanhaj.bytelog.services;

import com.javidanhaj.bytelog.domain.entities.Post;
import com.javidanhaj.bytelog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
}
