package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostTag extends BaseEntity {
    static public PostTag breakingNews = new PostTag();
    static public PostTag staffOnly = new PostTag();

    private String name;
    private Integer color;
}
