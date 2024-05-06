package com.homethunder.homethunder.useCase.post.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public interface ICreatePostDTO {
    String title();

    String content();

    String description();

    UUID image();

    LocalDateTime dateTimePublish();

    UUID postCategoryID();


}
