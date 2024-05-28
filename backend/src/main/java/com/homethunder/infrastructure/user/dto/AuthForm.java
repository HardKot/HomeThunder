package com.homethunder.infrastructure.user.dto;

import com.homethunder.useCase.user.dto.IUserLogin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


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
