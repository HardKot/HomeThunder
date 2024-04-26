package com.homethunder.homethunder.useCase.user.dto;


import com.homethunder.homethunder.domain.user.Gender;

import java.time.LocalDate;

public interface IUserUpdate {
    String firstname();
    String lastname();
    String patronymic();

    String avatarURI();
    Gender gender();
    LocalDate birthday();
}
