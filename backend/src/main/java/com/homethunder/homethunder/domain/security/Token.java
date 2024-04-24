package com.homethunder.homethunder.domain.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@Data
public class Token {
    private UUID id;

    private UUID uid;

    private String deviceName;

    private String ip;

    private LocalDateTime createAt;

    public boolean isRefreshing() {
        return createAt.plusDays(7).isAfter(LocalDateTime.now());
    }
}
