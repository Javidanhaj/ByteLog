package com.javidanhaj.bytelog.services;


import com.javidanhaj.bytelog.domain.entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);
}
