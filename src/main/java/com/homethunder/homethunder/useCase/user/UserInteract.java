package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.domain.user.IUserGateway;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.Setter;


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

}
