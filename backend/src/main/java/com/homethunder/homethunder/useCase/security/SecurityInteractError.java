package com.homethunder.homethunder.useCase.security;

public sealed class SecurityInteractError {
    public static final class TokenIsNoRefreshing extends SecurityInteractError {
    }

    public static final class EmailAndPasswordNoMatch extends SecurityInteractError {
    }

    public static final class TokenNotExists extends SecurityInteractError {
    }

    public static final class UserNotExists extends SecurityInteractError {
    }

    public static final class ErrorAuthWithJWT extends SecurityInteractError {
    }

    public static final class JWTExpired extends SecurityInteractError {
    }
}

