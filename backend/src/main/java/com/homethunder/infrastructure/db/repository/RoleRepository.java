package com.homethunder.infrastructure.db.repository;

import com.homethunder.infrastructure.db.schema.RoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleSchema, UUID> {
    Optional<RoleSchema> findByName(String name);
}
