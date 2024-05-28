package com.homethunder.domain.post;

import com.homethunder.domain.BaseEntity;
import com.homethunder.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    private String title;
    private String content;

    private String description;
    private UUID image;

    private LocalDateTime dateTimePublish;

    private User author;
    private PostCategory category;
    private Set<PostTag> tags = Set.of();

    public Set<PostTag> getTags() {
        Set<PostTag> tags = new HashSet<>(this.tags);
        if (Objects.nonNull(category)) tags.addAll(category.getTags());
        return tags;
    }

}
