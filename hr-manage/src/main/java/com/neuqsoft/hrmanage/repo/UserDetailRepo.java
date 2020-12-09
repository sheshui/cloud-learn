package com.neuqsoft.hrmanage.repo;

import com.neuqsoft.hrmanage.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sheshui
 * @date 2020/12/9
 * @description
 */
public interface UserDetailRepo extends JpaRepository<UserDetail, String> {
}
