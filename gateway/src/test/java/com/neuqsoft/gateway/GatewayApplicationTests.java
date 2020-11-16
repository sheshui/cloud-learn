package com.neuqsoft.gateway;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

//@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
        Flux.just("tomsdf", "jack", "lead")
                .filter(s -> s.contains("a"))

                .subscribe(System.out::println);
    }

    @Test
    void reactor() {
        Flux.just("tom")
                .map(s -> {
                    System.out.println("(concat @qq.com) at [" + Thread.currentThread() + "]");
                    return s.concat("@qq.com");
                })
                .publishOn(Schedulers.newSingle("thread-a"))
                .map(s -> {
                    System.out.println("(concat foo) at [" + Thread.currentThread() + "]");
                    return s.concat("foo");
                })
                .filter(s -> {
                    System.out.println("(startsWith f) at [" + Thread.currentThread() + "]");
                    return s.startsWith("t");
                })
                .publishOn(Schedulers.newSingle("thread-b"))
                .map(s -> {
                    System.out.println("(to length) at [" + Thread.currentThread() + "]");
                    return s.length();
                })
                .subscribeOn(Schedulers.newSingle("source"))
                .subscribe(System.out::println);
    }

}
