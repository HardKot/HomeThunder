package com.homethunder.homethunder.infrastructure.security;


import com.homethunder.homethunder.useCase.security.SecurityInteract;
import com.homethunder.homethunder.useCase.security.SecurityInteractError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.View;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final SecurityInteract securityInteract;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        String token = header.split(" ")[1];

        var result = securityInteract.authenticationByJWT(token);
        if (result.getSuccess().isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }
        if (result.getFailure().isPresent()) {
            var error = result.getFailure().get();
            switch (error) {
                case SecurityInteractError.UserNotExists userNotExists ->
                        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "User not found");
                case SecurityInteractError.JWTExpired jwtExpired ->
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "JWT expired");
                case SecurityInteractError.TokenNotExists tokenNotExists ->
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token not found");
                case SecurityInteractError.ErrorAuthWithJWT errorAuthWithJWT ->
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT");
                default -> response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error");
            }
            return;
        }
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getHeader("Authorization") == null;
    }
}
