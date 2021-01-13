package com.neuqsoft.webfluxlearning.api

import com.neuqsoft.webfluxlearning.entity.Icon
import com.neuqsoft.webfluxlearning.entity.UserAuth
import com.neuqsoft.webfluxlearning.repo.IconRepo
import com.neuqsoft.webfluxlearning.repo.UserAuthRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.annotation.Resource

/**
 * @author sunjiarui
 * @Date 2020/10/12
 */
@RestController
class MainController {
    @Autowired
    lateinit var userAuthRepo: UserAuthRepo

    @Autowired
    lateinit var iconRepo: IconRepo

    @Resource
    var dbClient: DatabaseClient? = null

    @get:GetMapping("/hello")
    val hello: Mono<String>
        get() = Mono.justOrEmpty("helloworld")

    @get:GetMapping("/user")
    val user: Flux<UserAuth?>
        get() = userAuthRepo.findAll()

    @get:GetMapping("/allUser")
    val allUser: Flux<UserAuth>
        get() = dbClient!!.select().from(UserAuth::class.java).`as`(
            UserAuth::class.java
        ).all()

    @get:GetMapping("/userNames")
    val userName: Flux<String>
        get() = userAuthRepo.findAll().map { it?.userName }

    @GetMapping("/icon/list")
    fun icon(): Flux<Icon?> {
        return iconRepo.findAll()
    }

}