//package com.neuqsoft.gateway.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @author sunjiarui
// * @Date 2020/9/25
// */
//@Slf4j
//@Component
//public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Override
//    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {
//        ServerWebExchange exchange = context.getExchange();
//        return authentication.map(auth -> new AuthorizationDecision(checkAuth(exchange, auth)));
//    }
//
//    private boolean checkAuth(ServerWebExchange exchange, Authentication authentication) {
//        log.info("auth:{}", authentication);
//        if (authentication instanceof OAuth2Authentication) {
//            OAuth2Authentication auth = (OAuth2Authentication) authentication;
//            String clientId = auth.getOAuth2Request().getClientId();
//            log.info("clientId is {}", clientId);
//        }
//        Object pricipal = authentication.getPrincipal();
//        log.info("用户信息：{}", pricipal.toString());
//        User user = (User) pricipal;
//        log.info("{}|{}|{}", exchange.getRequest().getMethod(), user.getUsername(), exchange.getRequest().getURI().getPath());
//        return true;
//    }
//}
