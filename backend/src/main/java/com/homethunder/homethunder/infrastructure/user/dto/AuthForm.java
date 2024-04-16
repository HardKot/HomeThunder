package com.homethunder.homethunder.infrastructure.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.useCase.user.dto.IUserLogin;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AuthForm(
        @NotEmpty
        @Email
        String email,

        @NotEmpty
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^[a-zA-Z\\d%*()?@#$~{}]+$")
        String password,

        boolean rememberMe
) implements IUserLogin {

}
