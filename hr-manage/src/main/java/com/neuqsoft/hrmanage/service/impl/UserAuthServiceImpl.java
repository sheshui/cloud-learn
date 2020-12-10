package com.neuqsoft.hrmanage.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.entity.UserDetail;
import com.neuqsoft.hrmanage.repo.UserAuthRepo;
import com.neuqsoft.hrmanage.repo.UserDetailRepo;
import com.neuqsoft.hrmanage.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 用户服务实现类
 *
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserAuthRepo userAuthRepo;
    @Autowired
    UserDetailRepo userDetailRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 保存用户信息
     *
     * @param userAuth 用户信息实体
     * @return ReturnMassage<String> 返回信息
     */
    @Override
    public ReturnMassage<String> saveUserAuth(UserAuth userAuth) {
        if (userAuthRepo.findByUserName(userAuth.getUserName()) != null) {
            return new ReturnMassage<>("-1", "用户名已存在");
        }
        userAuth.setUserPwd(passwordEncoder.encode(userAuth.getUserPwd()));
        userAuthRepo.save(userAuth);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public ReturnMassage<String> saveUserAuths(List<UserAuth> userAuths) {
        userAuthRepo.saveAll(userAuths);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public ReturnMassage<String> saveUserAuths(MultipartFile file) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        ExcelReader reader = ExcelUtil.getReader(in, 0);
        List<UserAuth> userAuths = reader.readAll(UserAuth.class);
        userAuthRepo.saveAll(userAuths);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public Page<UserAuth> findAll(int pageSize, int pageNo) {
        PageRequest request = PageRequest.of(pageSize, pageNo);
        return userAuthRepo.findAll(request);
    }

    @Override
    public ReturnMassage<String> delUser(List<String> userIds) {
        List<UserAuth> userAuths = userAuthRepo.findAllById(userIds);
        userAuthRepo.deleteAll(userAuths);
        return new ReturnMassage<>("0", "删除成功");
    }

    @Override
    public ReturnMassage<String> saveUserDetail(UserDetailDto detail) {
        UserAuth userInfo = new UserAuth();
        BeanUtils.copyProperties(detail, userInfo);
        userInfo.setUserPwd(passwordEncoder.encode(userInfo.getUserPwd()));
        userAuthRepo.save(userInfo);
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(detail, userDetail);
        userDetailRepo.save(userDetail);
        return new ReturnMassage<>("0", "保存成功");
    }


}
