package com.neuqsoft.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author sheshui
 * @date 2020/8/11
 * @description
 */

@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain springSevurityFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange()
                .pathMatchers("/api/**").hasAnyAuthority("Role_User")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
