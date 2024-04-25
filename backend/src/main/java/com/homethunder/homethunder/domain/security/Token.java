package com.homethunder.homethunder.domain.security;

import com.homethunder.homethunder.domain.Rule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@Data
public class Token {
    public Token() {}

    private UUID id = UUID.randomUUID();

    private UUID uid;

    private String deviceName;

    private String ip;

    private Set<Rule> ruleSet = Set.of();

    private LocalDateTime createAt = LocalDateTime.now();

    public boolean isRefreshing() {
        return createAt.plusDays(7).isAfter(LocalDateTime.now());
    }

    private boolean hasAccess(Rule rule) {
        return ruleSet.contains(rule);
    }

}
