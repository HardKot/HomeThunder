package com.homethunder.useCase.post.dto;

import java.util.UUID;

public interface IUpdatePostCategoryDTO {
    String name();
    String description();
    UUID imageID();
    UUID parentCategoryID();
}
