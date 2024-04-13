package com.homethunder.homethunder.useCase.user.dto;


import com.homethunder.homethunder.domain.user.Gender;

import java.time.LocalDate;

public interface IUserLogin {
    String email();
    String password();
}
