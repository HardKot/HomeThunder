package com.homethunder.homethunder.infrastructure.security;

import com.homethunder.homethunder.domain.Rule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtRequestFilter jwtRequestFilter) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/registration").permitAll();

//                    request.requestMatchers(HttpMethod.PUT, "/user/:id").hasRole(Rule.userChange.name());
//                    request.requestMatchers(HttpMethod.DELETE, "/user/:id").hasRole(Rule.userDelete.name());
//
//                    request.requestMatchers(HttpMethod.POST, "/user/:id/blocked").hasRole(Rule.userBlocked.name());
//                    request.requestMatchers(HttpMethod.DELETE, "/user/:id/userUnblocked").hasRole(Rule.userUnblocked.name());
//
//                    request.requestMatchers(HttpMethod.GET, "/user/:id/role").hasRole(Rule.userRoleShow.name());
//                    request.requestMatchers(HttpMethod.POST, "/user/:id/role").hasRole(Rule.userRoleAdded.name());
//                    request.requestMatchers(HttpMethod.DELETE, "/user/:id/role").hasRole(Rule.userRoleRemoved.name());
//                    request.requestMatchers(HttpMethod.POST, "/user/:id/rule").hasRole(Rule.userRuleSet.name());
//
//
//                    request.requestMatchers(HttpMethod.POST, "/role").hasRole(Rule.roleCreated.name());
//                    request.requestMatchers(HttpMethod.DELETE, "/role").hasRole(Rule.roleDeleted.name());
//                    request.requestMatchers(HttpMethod.PUT, "/role").hasRole(Rule.roleChange.name());

                    request.anyRequest().authenticated();
                })
//            .exceptionHandling(request -> {
//                request.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//            })
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
