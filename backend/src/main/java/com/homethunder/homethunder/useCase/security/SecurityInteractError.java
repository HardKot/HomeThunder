package com.homethunder.homethunder.useCase.security;

public sealed class SecurityInteractError {
    public static final class TokenIsNoRefreshing extends SecurityInteractError {}
    public static final class EmailAndPasswordNoMatch extends SecurityInteractError {}
}
