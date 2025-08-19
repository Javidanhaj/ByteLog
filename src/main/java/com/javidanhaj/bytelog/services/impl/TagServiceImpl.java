package com.javidanhaj.bytelog.services.impl;

import com.javidanhaj.bytelog.domain.entities.Tag;
import com.javidanhaj.bytelog.repositories.TagRepository;
import com.javidanhaj.bytelog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
}
