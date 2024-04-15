package com.homethunder.homethunder.domain;

import lombok.*;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class BaseEntity {
    private UUID id = UUID.randomUUID();

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @Nullable
    private LocalDateTime deletedAt;


    boolean getIsDeleted() {
        if (deletedAt == null) {
            return false;
        }
        return deletedAt.isAfter(LocalDateTime.now());
    }

    public void softDelete() {
        deletedAt = LocalDateTime.now();
    }

    public void softDelete(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void recover() {
        deletedAt = null;
    }
}
