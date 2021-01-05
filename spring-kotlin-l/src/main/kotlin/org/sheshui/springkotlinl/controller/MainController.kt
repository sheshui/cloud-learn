package org.sheshui.springkotlinl.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author sheshui
 * @date 2021/1/1
 * @description
 */
@RestController
class MainController {
    @GetMapping
    fun sum(name: String): String {
        return "hello $name"
    }
}