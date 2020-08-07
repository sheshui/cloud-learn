package com.neuqsoft.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author sunjiarui
 * @Date 2020/8/7
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 安全拦截机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 放行所有请求认证请求
                .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                //  其他请求必须认证
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    /**
     * token持久化
     */
    @Bean
    public TokenStore tokenStore() {
//        return new RedisTokenStore();
        return new InMemoryTokenStore();
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
