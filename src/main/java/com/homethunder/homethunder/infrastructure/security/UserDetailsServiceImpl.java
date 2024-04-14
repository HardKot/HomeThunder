package com.homethunder.homethunder.infrastructure.security;

import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.db.schema.UserSchema;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        UserSchema userSchema = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return UserDetailsImpl.build(userSchema.toUser());
    }
}
