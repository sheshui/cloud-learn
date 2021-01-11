package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.UserAuth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * userAuth实体类
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, String> {
    /**
     * 按照用户名查找用户实体
     *
     * @param userName 用户名
     * @return UserAuth 用户实体类
     */
    UserAuth findByUserName(String userName);


    /**
     * 按照用户名查找邮箱实体
     *
     * @param userMail 用户名
     * @return UserAuth 用户实体类
     */
    UserAuth findByUserEmail(String userMail);


    /**
     * 按照用户名查找邮箱实体
     *
     * @param userPhone 用户名
     * @return UserAuth 用户实体类
     */
    UserAuth findByUserPhone(String userPhone);

    /**
     * 搜索所有符合条件的用户信息
     *
     * @param spec     条件语句
     * @param pageable 分页信息
     * @return 用户分页表
     */
    Page<UserAuth> findAll(Specification<UserAuth> spec, Pageable pageable);
}
