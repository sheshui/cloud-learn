//package com.neuqsoft.gateway.config;
//
//import com.sun.org.apache.xpath.internal.operations.And;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
///**
// * 资源服务期配置
// * @author sheshui
// * @date 2020/8/8
// * @description
// */
//@Configuration
//@AllArgsConstructor
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    /**
//     * token服务配置
//     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resource){
//        resource.tokenServices(tokenServices());
//    }
////
////    @Bean
////    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http){
////        http.authorizeExchange()
////                .pathMatchers("/auth/**").permitAll()
////                .anyExchange().authenticated();
////        http.oauth2Client().authenticationConverter();
////
////        return http.build();
////    }
//    /**
//     * 路由安全认证配置
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/hr/**").authenticated()
//                .and().csrf().disable();
//    }
//
//    /**
//     * jwt token 校验
//     */
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    /**
//     * token转换器，和认证服务器一致
//     */
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter(){
//        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
//        converter.setSigningKey("sheshui");
//        return converter;
//    }
//
//    /**
//     * 资源服务器令牌解析服务
//     */
//    @Bean
//    @Primary
//    public ResourceServerTokenServices tokenServices(){
//        RemoteTokenServices tokenServices=new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("/auth/oauth/check_token");
//        tokenServices.setClientId("client_1");
//        tokenServices.setClientSecret("123456");
//        return tokenServices;
//    }
//}
