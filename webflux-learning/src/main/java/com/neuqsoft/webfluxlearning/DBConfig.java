//package com.neuqsoft.webfluxlearning;
//
//import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
//import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
///**
// * @author sunjiarui
// * @Date 2020/10/12
// */
//@Configuration
//@EnableR2dbcRepositories
//public class DBConfig extends AbstractR2dbcConfiguration {
//    @Override
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
//                .host("81.70.14.217")
//                .port(13306)
//                .username("root")
//                .password("[370804sjr]")
//                .database("hr")
//                .build());
//    }
//}
