package com.homethunder.domain.security;

import com.homethunder.domain.Rule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
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
