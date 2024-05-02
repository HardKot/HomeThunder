package com.homethunder.homethunder.useCase.post;

import com.homethunder.homethunder.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreatePostDTO {
    String title();

    String content();

    String description();

    UUID image();

    LocalDateTime dateTimePublish();

    User author();

    UUID PostCategoryID();
}
