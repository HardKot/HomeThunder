package com.homethunder.useCase.post.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public interface IUpdatePostDTO {
    String title();

    String content();

    String description();

    UUID image();

    LocalDateTime dateTimePublish();

    UUID PostCategoryID();
}
