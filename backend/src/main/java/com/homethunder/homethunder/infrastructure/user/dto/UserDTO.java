package com.homethunder.homethunder.infrastructure.user.dto;


import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.user.dto.IUserDTO;

public record UserDTO(
        String id,
        String firstname,
        String lastname,
        String patronymic,
        String avatarURI,
        String gender,
        String birthday,
        String phone,
        String email,
        String bannedBefore,

        String createdAt,
        String updatedAt,
        String deletedAt
) implements IUserDTO {
    public UserDTO(User user) {
        this(
                user.getId().toString(),
                user.getFirstname(),
                user.getLastname(),
                user.getPatronymic(),
                user.getAvatarURI(),
                user.getGender().toString(),
                user.getBirthday().toString(),
                user.getPhone(),
                user.getEmail(),
                user.getBannedBefore() != null ? user.getBannedBefore().toString() : null,
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString(),
                user.getDeletedAt() != null ? user.getDeletedAt().toString() : null
        );
    }
}
