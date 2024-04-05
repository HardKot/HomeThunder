package com.homethunder.homethunder.infrastructure.user.dto;

import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import jakarta.validation.constraints.*;

public record RegistrationForm(
        @NotEmpty
        String firstname,

        @NotEmpty
        String lastname,

        String patronymic,

        @NotEmpty
        String gender,

        @NotEmpty
        @Past
        String birthday,

        @NotEmpty
        @Pattern(regexp = "^\\d+$")
        String phone,

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
        String confirmPassword
) implements IUserRegistration {

}
