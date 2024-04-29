package com.homethunder.homethunder.domain.security;

import com.homethunder.homethunder.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface ISecurityGateway {
    String generateJWT(Token token);
    Token saveToken(Token token);
    void deleteToken(Token token);
    Optional<User> findUserByEmail(String email);
    boolean authenticateInManager(User user, String password);
    UUID extractTokenID(String token);
    Optional<Token> findTokenById(UUID id);
    Optional<User> findUserByUID(UUID id);
    boolean authenticateInManager(User user);
}
