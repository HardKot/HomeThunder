package com.homethunder.homethunder.infrastructure.user;


import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.domain.user.IUserGateway;
import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.db.schema.UserSchema;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
@AllArgsConstructor
public class UserGateway implements IUserGateway {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String generateTokenForEmail(User user, String metaName) {
        return "";
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(new UserSchema(user)).toUser();
    }

    @Override
    public User update(User user) {
        UserSchema userSchema = new UserSchema(user);
        userSchema.setUpdatedAt(LocalDateTime.now());

        return this.userRepository.save(userSchema).toUser();
    }

    @Override
    public User delete(User user) {
        UserSchema userSchema = new UserSchema(user);
        userSchema.setDeletedAt(LocalDateTime.now());

        return this.userRepository.save(userSchema).toUser();
    }

    @Override
    public void forseDelete(User user) {
        this.userRepository.delete(new UserSchema(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserSchema::toUser);
    }

    @Override
    public void sendEmail(User user, String template) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    }
}
