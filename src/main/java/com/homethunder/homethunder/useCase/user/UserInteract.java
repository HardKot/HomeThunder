package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.domain.user.UserGateway;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class UserInteract {
    private final UserGateway userDatabaseGateway;

    public UserInteract(UserGateway userDatabaseGateway) {
        this.userDatabaseGateway = userDatabaseGateway;
    }

    public Result<User, UserInteractError> registration(IUserRegistration dto) {
        if (!dto.password().equals(dto.confirmPassword())) return Results.failure(new UserInteractError.PasswordNotConfirm());
        if (userDatabaseGateway.findByEmail(dto.email()).isPresent()) return Results.failure(new UserInteractError.EmailExists());

        User user = new User();

        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setPatronymic(dto.patronymic());

        if (dto.gender() != null) user.setGender(Gender.valueOf(dto.gender()));
        user.setBirthday(LocalDate.parse(dto.birthday() , DateTimeFormatter.ISO_LOCAL_DATE));
        user.setAvatarURI(dto.avatarURI());

        user.setEmail(dto.email());
        user.setPhone(dto.phone());

        return Results.success(userDatabaseGateway.create(user));
    }

}
