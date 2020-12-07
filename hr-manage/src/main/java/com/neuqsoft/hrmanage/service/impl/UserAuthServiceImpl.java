package com.neuqsoft.hrmanage.service.impl;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.repo.UserAuthRepo;
import com.neuqsoft.hrmanage.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserAuthRepo userAuthRepo;

    /**
     * 保存用户信息
     *
     * @param userAuth 用户信息实体
     * @return ReturnMassage<String> 返回信息
     */
    @Override
    public ReturnMassage<String> saveUserAuth(UserAuth userAuth) {
        userAuthRepo.save(userAuth);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public ReturnMassage<String> saveUserAuths(List<UserAuth> userAuths) {
        userAuthRepo.saveAll(userAuths);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public ReturnMassage<String> saveUserAuths(MultipartFile file) {
        return null;
    }

    @Override
    public Page<UserAuth> findAll(int pageSize, int pageNo) {
        return null;
    }

    @Override
    public ReturnMassage<String> delUser(List<String> userIds) {
        return null;
    }

    @Override
    public ReturnMassage<String> saveUserDetail(UserDetailDto detail) {
        return null;
    }


}
