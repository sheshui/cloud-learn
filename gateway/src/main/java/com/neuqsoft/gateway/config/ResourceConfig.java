//package com.neuqsoft.gateway.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@AllArgsConstructor
//@Configuration
//@EnableWebFluxSecurity
//public class ResourceConfig {
//    @Autowired
//    AuthSuccessHandler authenticationManager;
//
//    @Autowired
//    RedisConnectionFactory redisFactory;
//
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.oauth2ResourceServer().jwt().jwkSetUri("http://localhost:8020/rsa/publicKey");
//        http.authorizeExchange()
//                //白名单配置
//                .pathMatchers("/auth/**", "*/anonymous/**").permitAll()
//                //鉴权管理器配置
//                .anyExchange().access(authenticationManager)
//                .and().exceptionHandling()
////                .accessDeniedHandler(restfulAccessDeniedHandler)//处理未授权
////                .authenticationEntryPoint(restAuthenticationEntryPoint)//处理未认证
//                .and().csrf().disable();
//        return http.build();
//    }
//
//
////    @Bean
////    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> accessTokenConverter() {
////        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
////        return new ReactiveJwtAuthenticationConverterAdapter(converter);
////    }
//
//
//}
