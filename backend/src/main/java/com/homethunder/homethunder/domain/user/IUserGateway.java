package com.homethunder.homethunder.domain.user;

import com.homethunder.homethunder.domain.security.Token;

import java.util.Optional;
import java.util.UUID;

public interface IUserGateway {
    User create(User user);
    User update(User user);
    User delete(User user);
    void forseDelete(User user);

    Optional<User> findByEmail(String email);

    void sendEmail(User user, String template);

    String passwordEncoder(String password);

    String generateTokenForEmail(User user, String metaName);
}
