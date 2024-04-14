package com.homethunder.homethunder.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("Authorization")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token != null) {
            String email = null;

            try {
                email = jwtService.extractEmail(token);
            } catch (ExpressionException e) {
                log.debug("Токен мертв");
            }

            List<SimpleGrantedAuthority> grantedAuthorityList = List.of();
            for (String rule: jwtService.extractRule(token)) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(rule));
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    grantedAuthorityList
            );

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
