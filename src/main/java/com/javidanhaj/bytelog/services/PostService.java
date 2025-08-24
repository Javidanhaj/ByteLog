package com.javidanhaj.bytelog.services;

import com.javidanhaj.bytelog.domain.CreatePostRequest;
import com.javidanhaj.bytelog.domain.UpdatePostRequest;
import com.javidanhaj.bytelog.domain.entities.Post;
import com.javidanhaj.bytelog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post getPost(UUID id);

    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getDraftPosts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);

    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

    void deletePost(UUID id);
}
