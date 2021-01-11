package org.sheshui.springkotlinl.controller

import org.sheshui.springkotlinl.entity.FunctionEntity
import org.sheshui.springkotlinl.entity.UserAuth
import org.sheshui.springkotlinl.repo.FunctionRepo
import org.sheshui.springkotlinl.repo.UserAuthRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Flux

/**
 *
 * @author sheshui
 * @date 2021/1/1
 * @description
 */
@RestController
class MainController(
    val userAuthRepo: UserAuthRepo,
    val funcRepo: FunctionRepo,
    val restTemplate: RestTemplate
) {

    @GetMapping
    fun sum(name: String): String {
        return "hello $name"
    }


    @GetMapping("/func")
    fun all(): Flux<FunctionEntity> {

        return funcRepo.findAll()
    }

    @GetMapping("/user")
    fun userAll(): Flux<UserAuth> {

        return userAuthRepo.findAll()
    }

}
