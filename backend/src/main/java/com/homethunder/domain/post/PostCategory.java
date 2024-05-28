package com.homethunder.domain.post;

import com.homethunder.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory extends BaseEntity {
    private String name;
    private String description;
    private UUID imageID;
    private PostCategory parent;
    private Set<PostTag> tags = Set.of();

    public Set<PostTag> getTags() {
        Set<PostTag> tags = new HashSet<>(this.tags);
        if (Objects.nonNull(parent)) tags.addAll(parent.getTags());
        return tags;
    }
}
