package com.neuqsoft.hrmanage.service.impl;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.repo.UserAuthRepo;
import com.neuqsoft.hrmanage.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserAuthRepo userAuthRepo;

    /**
     * 保存用户数据
     *
     * @param userAuth 用户信息
     */
    @Override
    public ReturnMassage<String> saveUserAuth(UserAuth userAuth) {
        userAuthRepo.save(userAuth);
        return new ReturnMassage<>("0", "保存成功");
    }
}
