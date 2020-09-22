package com.neuqsoft.gateway.config;

import com.neuqsoft.gateway.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author sheshui
 * @date 2020/8/11
 * @description
 */
@Order(99)
@Slf4j
public class AuthFilter implements GlobalFilter {
    /**
     * token名称：access_token
     */
    private static final String TOKEN_KEY = "access_token";
    @Autowired
    RestTemplate restTemplate;
    @Value("${auth.checkUrl:http://localhost:8020/oauth/check_token?token=}")
    String checkUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getQueryParams().get(TOKEN_KEY) != null) {
            String token = request.getQueryParams().getFirst(TOKEN_KEY);
            String checkAuthUrl = checkUrl + token;
            User user;
            try {
                user = restTemplate.getForEntity(checkAuthUrl, User.class).getBody();
                assert user != null;
            } catch (Exception e) {
                log.info("用户未认证");
                throw new GlobalException("-99999", e.getMessage());
            }
            request.getHeaders().set("userId", user.getUsername());
            request.getHeaders().set("userRole", user.getAuthorities().toString());
        } else {
            try {
                throw new GlobalException("-99999", "认证信息错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return chain.filter(exchange);
    }

}
