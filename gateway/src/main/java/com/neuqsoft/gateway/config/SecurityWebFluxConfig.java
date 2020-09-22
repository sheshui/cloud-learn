package com.neuqsoft.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author sunjiarui
 * @Date 2020/9/4
 */
//这个注解是关键！
@Configuration
@EnableWebFluxSecurity
public class SecurityWebFluxConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
//    //自定义的鉴权服务，通过鉴权的才能继续访问某个请求
//    @Autowired
//    private MyRBACServiceWebFlux myRBACServiceWebFlux;
//
//    //无权限访问被拒绝时的自定义处理器。如不自己处理，默认返回403错误
//    @Autowired
//    private MyAccessDeniedHandlerWebFlux myAccessDeniedHandlerWebFlux;
//
//    //登录成功时调用的自定义处理类
//    @Autowired
//    private LoginSuccessHandlerWebFlux loginSuccessHandlerWebFlux;
//
//    //登录失败时调用的自定义处理类
//    @Autowired
//    private LoginFailedHandlerWebFlux loginFailedHandlerWebFlux;
//
//    //成功登出时调用的自定义处理类
//    @Autowired
//    private LogoutSuccessHandlerWebFlux logoutSuccessHandlerWebFlux;
//
//    //未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口
//    @Autowired
//    private CustomHttpBasicServerAuthenticationEntryPointWebFlux customHttpBasicServerAuthenticationEntryPoint;

    //security的鉴权排除列表
    private static final String[] excludedAuthPages = {
            "/auth/**",
            "/health"
    };

    /**
     * 路由安全配置
     *
     * @param http 访问
     * @throws Exception
     */
//    @Override
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
//                .authenticationManager(myReactiveAuthenticationManager)//自定义登录验证。自动扫描注入，无需手动注入
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
                .and()
                .authorizeExchange().pathMatchers("/api/**").authenticated()
//                .authorizeExchange().pathMatchers("/**").access(myRBACServiceWebFlux)//自定义的鉴权服务，通过鉴权的才能继续访问某个请求
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/auth/login")//指定登录请求路径
//                .authenticationSuccessHandler(loginSuccessHandlerWebFlux) //认证成功
//                .authenticationFailureHandler(loginFailedHandlerWebFlux) //登陆验证失败
//                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //未登录访问资源时的处理类，若无此处理类，前端页面会弹出登录窗口
//                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandlerWebFlux)//访问被拒绝时自定义处理器
                .and().csrf().disable()//必须支持跨域
                .logout().logoutUrl("/auth/logout");
//                .logoutSuccessHandler(logoutSuccessHandlerWebFlux);//成功登出时调用的自定义处理类

        return http.build();
    }

    /**
     * token服务配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

    }

    /**
     * jwt token token校验器
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
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