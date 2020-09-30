package com.neuqsoft.journal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author sunjiarui
 * @Date 2020/9/30
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange((exchanges) -> {
            exchanges.pathMatchers("/**").hasRole("role");
        }).oauth2ResourceServer(oauth -> {
            oauth.jwt(Customizer.withDefaults());
        }).build();
    }
}
