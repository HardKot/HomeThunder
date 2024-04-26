package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;

import java.time.LocalDate;

public record UserRegistrationDTO(
        String firstname,
        String lastname,
        String patronymic,

        String avatarURI,
        Gender gender,
        LocalDate birthday,

        String email,
        String password,
        String confirmPassword,

        String ip,
        String deviceName
) implements IUserRegistration {

}
