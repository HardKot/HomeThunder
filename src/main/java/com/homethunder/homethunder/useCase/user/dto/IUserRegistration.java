package com.homethunder.homethunder.useCase.user.dto;


public interface IUserRegistration {
    String firstname();
    String lastname();
    String patronymic();

    String avatarURI();
    String gender();
    String birthday();

    String phone();
    String email();
    String password();
    String confirmPassword();

}
