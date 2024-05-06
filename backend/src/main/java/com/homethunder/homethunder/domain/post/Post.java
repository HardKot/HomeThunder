package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.BaseEntity;
import com.homethunder.homethunder.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
}
