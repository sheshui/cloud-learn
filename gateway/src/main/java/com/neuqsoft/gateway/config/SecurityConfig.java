package com.neuqsoft.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Arrays;

/**
 * @author sheshui
 * @date 2020/8/11
 * @description
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Qualifier("accessTokenConverter")
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
//    @Autowired
//    private AuthSuccessHandler authSuccessHandler;


    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // 令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);
        // 令牌有效期
        tokenServices.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }

    @Bean
    SecurityWebFilterChain springSevurityFilterChain(ServerHttpSecurity http) {
        log.info("开始进过滤");
        log.info(http.toString());


        return http
                .authorizeExchange()
//                .pathMatchers("/api/**").permitAll()
                .pathMatchers("/**/protected/**").denyAll()
                .pathMatchers("/**/apply/**").authenticated()
                .pathMatchers("/api/**", "/**/anonymous/**", "/auth/**").permitAll()
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
//                .oauth2ResourceServer()
//                .and()
                .build();
    }

    /**
     * 密码加密器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwt token解析器
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("sheshui");
        return converter;
    }
}
