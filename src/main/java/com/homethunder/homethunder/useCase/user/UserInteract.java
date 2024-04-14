package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.domain.user.IUserGateway;
import com.homethunder.homethunder.useCase.user.dto.IUserNewPassword;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import com.homethunder.homethunder.useCase.user.dto.IUserUpdate;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;


public class UserInteract {
    @Setter
    private IUserGateway userGateway;

    public Result<User, UserInteractError> registration(IUserRegistration dto) {
        if (!dto.password().equals(dto.confirmPassword())) return Results.failure(new UserInteractError.PasswordNotConfirm());
        if (userGateway.findByEmail(dto.email()).isPresent()) return Results.failure(new UserInteractError.EmailExists());

        User user = new User();

        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setPatronymic(dto.patronymic());

        if (dto.gender() != null) user.setGender(dto.gender());
        user.setBirthday(dto.birthday());

        user.setPassword(userGateway.passwordEncoder(dto.password()));

        user.setEmail(dto.email());

        return Results.success(userGateway.create(user));
    }

    public Result<User, UserInteractError> update(User user, IUserUpdate dto) {
        if (userGateway.findByEmail(dto.email()).isPresent() && !user.equals(userGateway.findByEmail(dto.email()).get())) return Results.failure(new UserInteractError.EmailExists());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setPatronymic(dto.patronymic());

        if (dto.gender() != null) user.setGender(dto.gender());
        user.setBirthday(dto.birthday());
        user.setEmail(dto.email());

        user.setDeletedAt(null);

        return Results.success(userGateway.update(user));
    }

    public Result<User, UserInteractError> changePassword(User user, IUserNewPassword dto) {
        if (!dto.password().equals(dto.confirmPassword())) return Results.failure(new UserInteractError.PasswordNotConfirm());

        user.setPassword(userGateway.passwordEncoder(dto.password()));

        return Results.success(userGateway.update(user));
    }

    public Result<User, UserInteractError> selfDelete(User user) {
        user.setDeletedAt(LocalDateTime.now().plus(Duration.ofDays(30)));

        return Results.success(userGateway.update(user));
    }

    public Result<User, UserInteractError> recovery(User user) {
        user.setDeletedAt(null);

        return Results.success(userGateway.update(user));
    }

}
