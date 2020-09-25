package com.neuqsoft.gateway.config;

/**
 * @author sunjiarui
 * @Date 2020/9/25
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ResourceServerManager implements ReactiveAuthenticationManager {

    @Autowired
    TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof OAuth2AccessToken)
                .cast(OAuth2AccessToken.class)
                .map(OAuth2AccessToken::getValue)
                .flatMap((accessToken -> {
                    log.info("accessToken is: {}", accessToken);
                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
                    if (oAuth2AccessToken == null) {
                        return Mono.error(new InvalidTokenException("密钥不正确，请检查"));
                    } else if (oAuth2AccessToken.isExpired()) {
                        return Mono.error(new InvalidTokenException("密钥已过期，请重新登录"));
                    }
                    OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(accessToken);
                    if (oAuth2Authentication == null) {
                        return Mono.error(new InvalidTokenException("Access Token 无效"));
                    } else {
                        return Mono.just(oAuth2Authentication);
                    }
                })).cast(Authentication.class);

    }
}
