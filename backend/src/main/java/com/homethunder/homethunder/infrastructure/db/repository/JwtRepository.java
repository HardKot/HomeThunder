package com.homethunder.homethunder.infrastructure.db.repository;

import com.homethunder.homethunder.infrastructure.db.schema.JwtSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JwtRepository extends JpaRepository<JwtSchema, UUID> {
    Optional<JwtSchema> findById(UUID id);
}
