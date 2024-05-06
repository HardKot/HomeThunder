package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
}
