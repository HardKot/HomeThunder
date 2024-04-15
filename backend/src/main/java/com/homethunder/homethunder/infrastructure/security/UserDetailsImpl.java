package com.homethunder.homethunder.infrastructure.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homethunder.homethunder.domain.Rule;
import com.homethunder.homethunder.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private UUID id;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetailsImpl build(User user) {
        Set<Rule> ruleSet = Set.of();

        for (User.RuleDetail ruleDetailSchema : user.getRuleDetailSet()) {
            if (ruleDetailSchema.isActive()) ruleSet.add(ruleDetailSchema.getRule());
        }


        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                ruleSet.stream().map(rule -> new SimpleGrantedAuthority(rule.name())).toList()
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
