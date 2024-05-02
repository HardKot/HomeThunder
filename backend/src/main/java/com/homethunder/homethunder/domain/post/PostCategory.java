package com.homethunder.homethunder.domain.post;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.*;

import java.util.UUID;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory extends BaseEntity {
    private String name;
    private String description;
    private UUID imageID;
    private PostCategory parent;
}
