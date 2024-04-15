package com.homethunder.homethunder.infrastructure.security;

import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.db.schema.UserSchema;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationFacade {
    private UserRepository userRepository;

    public UserSchema getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow();
    }
}
