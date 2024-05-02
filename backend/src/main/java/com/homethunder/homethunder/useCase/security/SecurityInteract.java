package com.homethunder.homethunder.useCase.security;

import com.homethunder.homethunder.domain.security.ISecurityGateway;
import com.homethunder.homethunder.domain.security.Token;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.security.dto.ILoginDTO;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;


public class SecurityInteract {
    @Setter
    private ISecurityGateway securityGateway;

    public Result<String, SecurityInteractError> refreshingToken(Token token) {
        securityGateway.deleteToken(token);
        if (!token.isRefreshing()) return Results.failure(new SecurityInteractError.TokenIsNoRefreshing());

        Token freshToken = new Token();
        freshToken.setUid(token.getUid());
        freshToken.setIp(token.getIp());
        freshToken.setRuleSet(token.getRuleSet());
        freshToken.setDeviceName(token.getDeviceName());

        String jwt = securityGateway.generateJWT(securityGateway.saveToken(freshToken));

        return Results.success(jwt);
    }

    public Result<String, SecurityInteractError> login(ILoginDTO dto) {
        Optional<User> userSearch = securityGateway.findUserByEmail(dto.email());
        if (userSearch.isEmpty() || !securityGateway.authenticateInManager(userSearch.get(), dto.password())) return Results.failure(new SecurityInteractError.EmailAndPasswordNoMatch());
        User user = userSearch.get();
        Token token = new Token();
        token.setIp(dto.ip());
        token.setUid(user.getId());
        token.setRuleSet(user.getActiveRule());
        token.setDeviceName(dto.deviceName());

        securityGateway.saveToken(token);

        return Results.success(securityGateway.generateJWT(token));
    }

    public void logout(Token token) {
        securityGateway.deleteToken(token);
    }

    public Result<User, SecurityInteractError> authenticationByJWT(String jwt) {
        if (securityGateway.jwtIsExpired(jwt)) return Results.failure(new SecurityInteractError.JWTExpired());
        UUID id = securityGateway.extractTokenID(jwt);
        Optional<Token> tokenSearch = securityGateway.findTokenById(id);
        if (tokenSearch.isEmpty()) return Results.failure(new SecurityInteractError.TokenNotExists());
        Optional<User> userSearch = securityGateway.findUserByUID(tokenSearch.get().getUid());
        if (userSearch.isEmpty()) return Results.failure(new SecurityInteractError.UserNotExists());
        if (!securityGateway.authenticateInManager(userSearch.get())) return Results.failure(new SecurityInteractError.ErrorAuthWithJWT());
        return Results.success(userSearch.get());
    }
}
