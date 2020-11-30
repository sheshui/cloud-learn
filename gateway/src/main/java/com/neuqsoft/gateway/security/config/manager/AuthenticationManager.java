package com.neuqsoft.gateway.security.config.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author sunjiarui
 */
@Slf4j
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MapReactiveUserDetailsService myUserDetailServiceImpl;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String userName = authentication.getName();
        String rawPwd = (String) authentication.getCredentials();

        Mono<UserDetails> user = null;
        log.info("userName:{} | pwd: {}", userName, rawPwd);
//        if ("jwttoken".equals(userName))
//        }


        try {
            user = myUserDetailServiceImpl.findByUsername(userName);
        } catch (Exception e) {
            return Mono.error(e);
        }
        Mono.from(user)
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Mono.just(authentication);
    }
}
