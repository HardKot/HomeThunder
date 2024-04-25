package com.homethunder.homethunder.domain.security;

import com.homethunder.homethunder.domain.user.User;

import java.util.Optional;

public interface ISecurityGateway {
    public String generateJWT(Token token);
    public Token save(Token token);
    public void delete(Token token);
    Optional<User> findByEmail(String email);
    boolean authenticateInManager(User user, String password);

}
