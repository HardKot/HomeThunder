package com.homethunder.useCase.security.dto;

public interface ILoginDTO {
    String email();

    String password();

    String ip();

    String deviceName();
}
