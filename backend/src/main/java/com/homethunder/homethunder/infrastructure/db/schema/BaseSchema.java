package com.homethunder.homethunder.infrastructure.db.schema;


import com.homethunder.homethunder.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseSchema {
    @Id
    @GeneratedValue()
    protected UUID id;

    @Column(name = "updated_at")
    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @Column(name = "created_at")
    @CreatedDate
    protected LocalDateTime createdAt;

    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;


    public boolean getIsDeleted() {
        if (deletedAt == null) return false;
        return deletedAt.isAfter(LocalDateTime.now());
    }


    protected <T extends BaseEntity> T getBaseEntity(T entity) {
        entity.setId(id);
        entity.setDeletedAt(deletedAt);
        entity.setUpdatedAt(updatedAt);
        entity.setCreatedAt(createdAt);
        return entity;
    }

    protected <T extends BaseEntity> void useBaseEntity(T entity) {
        id        = entity.getId();

        deletedAt = entity.getDeletedAt();
        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}