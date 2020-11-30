//package com.neuqsoft.gateway.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.util.Map;
//import java.util.function.Predicate;
//
///**
// * @author sunjiarui
// * @Date 2020/9/24
// */
//@Slf4j
//@Component
//public class AuthSuccessHandler implements ReactiveAuthorizationManager<AuthorizationContext> {
//
//    @Autowired
//    AntPathMatcher antPathMatcher;
//
//    @Override
//    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
//        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
//        URI uri = authorizationContext.getExchange().getRequest().getURI();
//        Map<String,String> list=(Map)request.getQueryParams();
//
//
//
//        log.info("{}|{}",uri,list);
//
//        return mono
//                .filter(new Predicate<Authentication>() {
//                    @Override
//                    public boolean test(Authentication t) {
//                        return t.isAuthenticated();
//                    }
//                })
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(pattern -> antPathMatcher.match(pattern, uri.getPath()))
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
//    }
//
//    @Override
//    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
//        System.out.println();
//        return check(authentication, object)
//                .filter( d -> d.isGranted())
//                .switchIfEmpty(Mono.defer(() -> Mono.error(new AccessDeniedException("Access Denied"))))
//                .flatMap( d -> Mono.empty() );
//    }
//
//    @Bean
//    AntPathMatcher antPathMatcher() {
//        return new AntPathMatcher();
//    }
//}
