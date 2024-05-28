package com.homethunder.infrastructure.db.repository;

import com.homethunder.infrastructure.db.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserSchema, UUID> {
    Optional<UserSchema> findByEmail(String email);
}
