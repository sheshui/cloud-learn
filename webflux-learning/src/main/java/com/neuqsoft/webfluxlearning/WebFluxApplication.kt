package com.neuqsoft.webfluxlearning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author sunjiarui
 * @Date 2020/10/10
 */
//@EnableDiscoveryClient
@SpringBootApplication
class WebFluxApplication

fun main(args: Array<String>) {
    runApplication<WebFluxApplication>(*args)
}