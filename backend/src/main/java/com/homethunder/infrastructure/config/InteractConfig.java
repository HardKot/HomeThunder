package com.homethunder.infrastructure.config;

import com.homethunder.infrastructure.security.SecurityGateway;
import com.homethunder.infrastructure.user.UserGateway;
import com.homethunder.useCase.security.SecurityInteract;
import com.homethunder.useCase.user.UserInteract;
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
