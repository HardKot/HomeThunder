package com.homethunder.homethunder.infrastructure.user;


import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.domain.user.UserGateway;
import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.db.schema.UserSchema;
import com.homethunder.homethunder.infrastructure.services.EmailSender;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;


public class UserDatabaseGateway implements UserGateway {
    @Setter
    private UserRepository userRepository;

    @Setter
    private EmailSender emailSender;


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

    }
}
