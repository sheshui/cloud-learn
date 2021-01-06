package org.sheshui.springkotlinl.controller

import org.sheshui.springkotlinl.FunctionEntity
import org.sheshui.springkotlinl.FunctionRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toMono

/**
 *
 * @author sheshui
 * @date 2021/1/1
 * @description
 */
@RestController
class MainController(val funcRepo: FunctionRepo, val restTemplate: RestTemplate) {

    @GetMapping
    fun sum(name: String): String {
        return "hello $name"
    }


    @GetMapping("/func")
    fun all(): Flux<FunctionEntity> {

        return funcRepo.findAll().flatMap { it.toMono() }
    }

}
