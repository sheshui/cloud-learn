package com.neuqsoft.hrmanage.service;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.entity.UserAuth;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
public interface UserAuthService {
    /**
     * @param userAuth
     * @return
     */
    ReturnMassage<String> saveUserAuth(UserAuth userAuth);
}
