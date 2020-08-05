package com.neuqsoft.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunjiarui
 * @Date 2020/8/5
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/game").uri("http://news.baidu.com/game"))
                .route(r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .build();
    }
}
