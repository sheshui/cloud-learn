package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
public interface UserAuthRepo extends JpaRepository<UserAuth, String> {
    UserAuth findByUserName(String userName);

}
