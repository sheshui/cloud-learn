package com.neuqsoft.hrmanage.service;

import com.neuqsoft.common.dto.ReturnMassage;
import com.neuqsoft.common.entity.UserAuth;

/**
 * 用户服务接口
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
public interface UserAuthService {
    /**
     * 保存用户信息
     * @param userAuth 用户信息实体
     * @return ReturnMassage<String> 返回信息
     */
    ReturnMassage<String> saveUserAuth(UserAuth userAuth);
}
