package com.neuqsoft.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author sheshui
 * @date 2020/8/11
 * @description
 */
@Order(99)
public class AuthFilter implements GlobalFilter {
    /**
     * token名称：access_token
     */
    private static final String TOKEN_KEY = "access_token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getQueryParams().get(TOKEN_KEY) != null) {
            String token = request.getQueryParams().getFirst(TOKEN_KEY);


        } else {
            try {
                throw new Exception("认证信息错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return chain.filter(exchange);
    }

}
