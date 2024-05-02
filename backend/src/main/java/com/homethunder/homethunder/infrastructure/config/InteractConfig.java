package com.homethunder.homethunder.infrastructure.config;

import com.homethunder.homethunder.infrastructure.security.SecurityGateway;
import com.homethunder.homethunder.infrastructure.user.UserGateway;
import com.homethunder.homethunder.useCase.security.SecurityInteract;
import com.homethunder.homethunder.useCase.user.UserInteract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class InteractConfig {
    @Bean
    public UserInteract getUserInteract(UserGateway userGateway) {
        UserInteract userInteract = new UserInteract();
        userInteract.setUserGateway(userGateway);
        return userInteract;
    }

    @Bean
    public SecurityInteract getSecurityInteract(SecurityGateway securityGateway) {
        SecurityInteract securityInteract = new SecurityInteract();
        securityInteract.setSecurityGateway(securityGateway);
        return securityInteract;
    }

}
