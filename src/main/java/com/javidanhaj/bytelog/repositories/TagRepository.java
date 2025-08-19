package com.javidanhaj.bytelog.repositories;

import com.javidanhaj.bytelog.domain.entities.Category;
import com.javidanhaj.bytelog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query("SELECT t FROM Tag t LEFT JOIN FETCH  t.posts")
    List<Tag> findAllWithPostCount();
}
