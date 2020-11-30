package com.neuqsoft.gateway.repo;

import com.neuqsoft.gateway.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sunjiarui
 * @Date 2020/9/21
 */
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, String> {
    /**
     * 按照用户id查找用户角色
     *
     * @param userId 用户id
     * @return
     */
    List<UserRole> findByUserId(String userId);
}
