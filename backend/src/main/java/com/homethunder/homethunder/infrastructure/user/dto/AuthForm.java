package com.homethunder.homethunder.infrastructure.user.dto;

import com.homethunder.homethunder.useCase.user.dto.IUserLogin;
import jakarta.validation.constraints.*;


public record AuthForm(
        @NotEmpty
        @Email
        String email,

        @NotEmpty
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^[a-zA-Z\\d%*()?@#$~{}]+$")
        String password
) implements IUserLogin {

}
