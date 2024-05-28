package com.homethunder.infrastructure.db.repository;

import com.homethunder.infrastructure.db.schema.TokenSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<TokenSchema, UUID> {
    Optional<TokenSchema> findById(UUID id);
}
