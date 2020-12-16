package com.neuqsoft.authserver.config;

import cn.hutool.crypto.asymmetric.RSA;
import com.neuqsoft.authserver.service.MyUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;

/**
 * @author sunjiarui
 * @Date 2020/8/7
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisFactory;
    @Autowired
    private MyUserDetailServiceImpl userDetailService;

    /**
     * 安全拦截机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 放行所有请求认证请求
                .authorizeRequests()
                .antMatchers("/auth/**", "/rsa/**")
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
        return new RedisTokenStore(redisFactory);
//        return new InMemoryTokenStore();
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /**
     * 认证管理器配置
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() {
        return authentication -> daoAuthenticationProvider().authenticate(authentication);
    }

    /**
     * 认证是由 AuthenticationManager 来管理的，
     * 但是真正进行认证的是 AuthenticationManager 中定义的 AuthenticationProvider，
     * 用于调用userDetailsService进行验证
     */
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

//    /**
//     * 用户详细服务
//     */
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        userDetailsService.createUser(User.withUsername("root").password(passwordEncoder().encode("root")).authorities("Role_User").build());
//        return userDetailsService;
//    }

    /**
     * 设置授权码模式的授权码存取，暂时采用内存方式
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    //    /**
//     * jwt token解析器
//     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }


    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        RSA rsa = new RSA();
        return new KeyPair(rsa.getPublicKey(), rsa.getPrivateKey());
    }

}
