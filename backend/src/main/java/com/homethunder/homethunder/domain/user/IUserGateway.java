package com.homethunder.homethunder.domain.user;

import java.util.Optional;

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
