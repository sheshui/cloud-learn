package com.neuqsoft.webfluxlearning.api;

import com.neuqsoft.webfluxlearning.entity.UserAuth;
import com.neuqsoft.webfluxlearning.repo.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author sunjiarui
 * @Date 2020/10/12
 */
@RestController
public class MainController {
    @Autowired
    UserAuthRepo userAuthRepo;
    @Resource
    DatabaseClient dbClient;

    @GetMapping("/hello")
    public Mono<String> getHello() {
        return Mono.justOrEmpty("helloworld");
    }


    @GetMapping("/user")
    public Flux<UserAuth> getUser() {
        return userAuthRepo.findAll();
    }


    @GetMapping("/allUser")
    public Flux<UserAuth> getAllUser() {
        return dbClient.select().from(UserAuth.class).as(UserAuth.class).all();
    }

    @GetMapping("/userNames")
    public Flux<String> getUserName() {
        return userAuthRepo.findAll().map(UserAuth::getUserName);
    }
}
