package com.javidanhaj.bytelog.mappers;

import com.javidanhaj.bytelog.domain.PostStatus;
import com.javidanhaj.bytelog.domain.dtos.TagResponse;
import com.javidanhaj.bytelog.domain.entities.Post;
import com.javidanhaj.bytelog.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if (posts == null) {
            return 0;
        }
        return (int) posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
