package com.homethunder.homethunder.useCase.user;

public sealed class UserInteractError {

    public static final class PasswordNotConfirm extends UserInteractError {  }

    public static final class EmailExists extends UserInteractError {}
}
