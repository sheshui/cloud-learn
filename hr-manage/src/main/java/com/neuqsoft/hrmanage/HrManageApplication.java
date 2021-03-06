package com.neuqsoft.hrmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sunjiarui
 * @Date 2020/08/03
 */
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class HrManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrManageApplication.class, args);
    }

}
