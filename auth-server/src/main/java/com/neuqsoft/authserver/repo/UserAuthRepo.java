package com.neuqsoft.authserver.repo;


import com.neuqsoft.authserver.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sunjiarui
 * @Date 2020/8/11
 */
@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, String> {
}
