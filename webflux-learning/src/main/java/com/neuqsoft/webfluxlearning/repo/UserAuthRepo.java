package com.neuqsoft.webfluxlearning.repo;

import com.neuqsoft.webfluxlearning.entity.UserAuth;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author sunjiarui
 * @Date 2020/10/12
 */
public interface UserAuthRepo extends ReactiveCrudRepository<UserAuth, String> {
    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    Flux<UserAuth> findAll();
}
