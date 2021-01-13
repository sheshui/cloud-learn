package com.neuqsoft.hrmanage.service;

import com.neuqsoft.hrmanage.dto.UserDetailDto;

/**
 * @author sunjiarui
 */
public interface UserService {
    /**
     * 查找用户名 -由创建者id查询创建人
     *
     * @param userId 用户id
     * @return
     */
    String getUserName(String userId);

    UserDetailDto getUserDetail(String userId);
}
