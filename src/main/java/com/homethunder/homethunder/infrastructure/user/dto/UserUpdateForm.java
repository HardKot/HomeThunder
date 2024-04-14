package com.homethunder.homethunder.infrastructure.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.useCase.user.dto.IUserUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UserUpdateForm(
        @NotEmpty
        String firstname,

        @NotEmpty
        String lastname,

        String patronymic,

        @NotNull
        Gender gender,

        @NotNull
        @Past
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate birthday,

        String avatarURI,

        @NotEmpty
        @Email
        String email
) implements IUserUpdate {
}
