package org.sheshui.springkotlinl.repo

import org.sheshui.springkotlinl.entity.UserAuth
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

/**
 * @author sunjiarui
 * @Date 2020/10/12
 */
interface UserAuthRepo : ReactiveCrudRepository<UserAuth, String> {
    /**
     * 查询所有数据
     *
     * @return
     */
    override fun findAll(): Flux<UserAuth>
}