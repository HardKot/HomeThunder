package com.homethunder.useCase.user.dto;

public interface IUserDTO {
    String id();

    String firstname();

    String lastname();

    String patronymic();

    String avatarURI();

    String gender();

    String birthday();

    String phone();

    String email();

    String bannedBefore();

    String createdAt();

    String updatedAt();

    String deletedAt();
}
