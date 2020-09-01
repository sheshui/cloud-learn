package com.neuqsoft.hrmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sunjiarui
 * @Date 2020/08/03
 */
//@EntityScan("com.neuqsoft.common")
@EnableDiscoveryClient
@SpringBootApplication
public class HrManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrManageApplication.class, args);
    }

}
