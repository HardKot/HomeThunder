package com.homethunder.homethunder.infrastructure.security;

import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.db.schema.UserSchema;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserSchema> userSchemaOptional = userRepository.findByEmail(email);
        if (userSchemaOptional.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + email);
        }

        return UserDetailsImpl.build(userSchemaOptional.get());
    }
}
