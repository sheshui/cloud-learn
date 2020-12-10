package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.UserAuth;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * userAuth实体类
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Repository
public interface UserAuthRepo extends PagingAndSortingRepository<UserAuth, String> {
    /**
     * 按照用户名查找用户实体
     *
     * @param userName 用户名
     * @return UserAuth 用户实体类
     */
    UserAuth findByUserName(String userName);

}
