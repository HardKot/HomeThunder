package com.homethunder.infrastructure.user.dto;

import com.homethunder.useCase.user.dto.IUserNewPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserNewPasswordForm(
        @NotEmpty
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^[a-zA-Z\\d%*()?@#$~{}]+$")
        String password,

        @NotEmpty
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^[a-zA-Z\\d%*()?@#$~{}]+$")
        String confirmPassword
) implements IUserNewPassword {

}
