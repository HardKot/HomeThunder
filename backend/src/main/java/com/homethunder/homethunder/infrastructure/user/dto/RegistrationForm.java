package com.homethunder.homethunder.infrastructure.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegistrationForm(
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
        String email,

        @NotEmpty
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^[a-zA-Z\\d%*()?@#$~{}]+$")
        String password,

        @NotEmpty
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^[a-zA-Z\\d%*()?@#$~{}]+$")
        String confirmPassword,

        @NotEmpty
        @Pattern(regexp = "\\d{1,3}.\\d{1,3}.\\d{1,3}")
        String ip,

        @NotEmpty
        @Pattern(regexp = "\\d{1,3}.\\d{1,3}.\\d{1,3}")
        String deviceName
) implements IUserRegistration {

}
