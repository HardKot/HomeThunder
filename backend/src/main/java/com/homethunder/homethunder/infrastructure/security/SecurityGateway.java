package com.homethunder.homethunder.infrastructure.security;

import com.homethunder.homethunder.domain.Rule;
import com.homethunder.homethunder.domain.security.ISecurityGateway;
import com.homethunder.homethunder.domain.security.Token;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.infrastructure.db.repository.TokenRepository;
import com.homethunder.homethunder.infrastructure.db.repository.UserRepository;
import com.homethunder.homethunder.infrastructure.db.schema.TokenSchema;
import com.homethunder.homethunder.infrastructure.db.schema.UserSchema;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SecurityGateway implements ISecurityGateway {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    @Override
    public String generateJWT(Token token) {
        return jwtService.generateToken(token);
    }

    @Override
    public Token saveToken(Token token) {
        UserSchema userSchema = userRepository.getReferenceById(token.getUid());
        return tokenRepository.save(TokenSchema.build(token, userSchema)).toToken();
    }

    @Override
    public void deleteToken(Token token) {
        tokenRepository.deleteById(token.getId());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserSchema::toUser);
    }

    @Override
    public boolean authenticateInManager(User user, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), password));
        } catch (BadCredentialsException e) {
            return false;
        }
        return true;
    }

    @Override
    public UUID extractTokenID(String token) {
        return jwtService.extractTokenID(token);
    }

    @Override
    public Optional<Token> findTokenById(UUID id) {
        return tokenRepository.findById(id).map(TokenSchema::toToken);
    }

    @Override
    public Optional<User> findUserByUID(UUID id) {
        return userRepository.findById(id).map(UserSchema::toUser);
    }

    @Override
    public boolean authenticateInManager(User user) {
        List<SimpleGrantedAuthority> grantedAuthorityList = List.of();
        for (Rule rule : user.getActiveRule()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(rule.name()));
        }

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null,
                    grantedAuthorityList
            );

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean jwtIsExpired(String token) {
        return jwtService.isTokenExpired(token);
    }
}
