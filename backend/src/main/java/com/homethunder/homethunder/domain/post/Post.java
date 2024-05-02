package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    private String title;
    private String content;

    private String description;
    private UUID image;

    private LocalDateTime dateTimePublish;

    private Author author;
    private PostCategory category;
}
