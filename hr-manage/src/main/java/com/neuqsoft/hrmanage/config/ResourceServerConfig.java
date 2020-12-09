package com.neuqsoft.hrmanage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author sunjiarui
 * @Date 2020/8/13
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private RedisConnectionFactory factory;


    /**
     * 路由安全配置
     *
     * @param http 访问
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("*/anonymous/**", "/swagger-ui.html").permitAll()
                .antMatchers("/api/**").access("#oauth2.hasAnyScope('self')")
                .antMatchers("*/manage/**").access("#oauth2.clientHasRole('HR')")
//                .antMatchers("/swagger-ui.html").permitAll()
                .and().csrf().disable();
    }

    /**
     * token服务配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore());
    }

    /**
     * jwt token token校验器
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(factory);
    }

    /**
     * Token转换器必须与认证服务一致
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("sheshui");
        return accessTokenConverter;
    }

}
