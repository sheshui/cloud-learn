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
    /**
     * 按照用户名查询用户
     *
     * @param userName 用户名
     * @return 查询到的用户
     */
    UserAuth findByUserName(String userName);

    /**
     * 按照注册邮箱查询用户
     *
     * @param userEmail 用户邮箱
     * @return 查询到的用户
     */
    UserAuth findByUserEmail(String userEmail);

    /**
     * 按照用户手机号查询用户
     *
     * @param userPhone 用户的手机号
     * @return 查询到的用户
     */
    UserAuth findByUserPhone(String userPhone);
}
