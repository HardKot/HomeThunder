package com.homethunder.homethunder.infrastructure.config;

import com.homethunder.homethunder.infrastructure.user.UserGateway;
import com.homethunder.homethunder.useCase.user.UserInteract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MvcConfig {
    @Bean
    public UserInteract getUserInteract(UserGateway userGateway) {
        UserInteract userInteract = new UserInteract();
        userInteract.setUserGateway(userGateway);
        return userInteract;
    }



}
