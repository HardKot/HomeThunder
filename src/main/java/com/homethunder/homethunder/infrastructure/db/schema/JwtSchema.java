package com.homethunder.homethunder.infrastructure.db.schema;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "jwt")
@Entity
@Data
@NoArgsConstructor
public class JwtSchema {
    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "created_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "device_name")
    private String deviceName;
}
