package com.neuqsoft.hrmanage.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.neuqsoft.hrmanage.config.user.UserHolder;
import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.entity.UserDetail;
import com.neuqsoft.hrmanage.repo.UserAuthRepo;
import com.neuqsoft.hrmanage.repo.UserDetailRepo;
import com.neuqsoft.hrmanage.service.UserAuthService;
import com.neuqsoft.hrmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    UserHolder userHolder;

    @Autowired
    UserService userService;

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
        if (!StringUtils.isEmpty(userAuth.getUserEmail()) && userAuthRepo.findByUserEmail(userAuth.getUserEmail()) != null) {
            return new ReturnMassage<>("-1", "邮箱已存在");
        }
        if (!StringUtils.isEmpty(userAuth.getUserPhone()) && userAuthRepo.findByUserPhone(userAuth.getUserPhone()) != null) {
            return new ReturnMassage<>("-1", "手机号已存在");
        }
        userAuth.setUserPwd(passwordEncoder.encode(userAuth.getUserPwd()));
        String modifyId = "";
        try {
            System.out.println(userHolder.getUid());
            modifyId = userHolder.getUid();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("匿名用户注册中。。。");
        }
        if (StringUtils.isEmpty(modifyId)) {
            modifyId = UUID.fastUUID().toString(true);
        }
        if (StringUtils.isEmpty(userAuth.getUserId())) {
            userAuth.setUserId(modifyId);
        }
        userAuth.setCreaterId(modifyId);
        userAuth.setCreateTime(DateUtil.now());
        userAuth.setUserStatus("1");
        log.info("{}", userAuth);
        userAuthRepo.save(userAuth);


        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userAuth.getUserId());
        userDetail.setUserAvatar("http://81.70.14.217/project-new/static/images/icon/g1.ico");
        userDetailRepo.save(userDetail);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public ReturnMassage<String> saveUserAuths(List<UserAuth> userAuths) {
        userAuths.forEach(userAuth -> {
            userAuth.setCreaterId(userHolder.getUid());
            userAuth.setCreateTime(DateUtil.now());
            if (StringUtils.isEmpty(userAuth.getUserId())) {
                userAuth.setUserId(UUID.fastUUID().toString(true));
            }
        });
        userAuthRepo.saveAll(userAuths);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public ReturnMassage<String> saveUserAuths(MultipartFile file) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        ExcelReader reader = ExcelUtil.getReader(in, 0);
        List<UserAuth> userAuths = reader.readAll(UserAuth.class);
        System.out.println("file处理：\n" + userAuths);
        userAuths.forEach(userAuth -> {
            userAuth.setCreateTime(DateUtil.now());
            if (StringUtils.isEmpty(userAuth.getUserId())) {
                userAuth.setUserId(UUID.fastUUID().toString(true));
            }
        });
        userAuthRepo.saveAll(userAuths);
        return new ReturnMassage<>("0", "保存成功");
    }


    @Override
    public Page<UserAuth> findAll(int pageSize, int pageNo) {
        PageRequest request = PageRequest.of(pageNo, pageSize);
        Page<UserAuth> page = userAuthRepo.findAll(request);
        page.getContent().forEach(userAuth -> {
            String createName = userService.getUserName(userAuth.getCreaterId());
            userAuth.setCreaterId(StringUtils.isEmpty(createName) ? userAuth.getUserName() : createName);
        });
        return page;
    }

    @Override
    public ReturnMassage<String> delUser(List<String> userIds) {
        List<UserAuth> userAuths = userAuthRepo.findAllById(userIds);
        userAuthRepo.deleteAll(userAuths);
        return new ReturnMassage<>("0", "删除成功");
    }

    @Override
    public ReturnMassage<String> saveUserDetail(UserDetailDto detail) {
        UserAuth userInfo = userAuthRepo.getOne(detail.getUserId());
        BeanUtils.copyProperties(detail, userInfo);
        userInfo.setUserPwd(passwordEncoder.encode(userInfo.getUserPwd()));
        userAuthRepo.save(userInfo);
        UserDetail userDetail = userDetailRepo.getOne(detail.getUserId());
        BeanUtils.copyProperties(detail, userDetail);
        userDetailRepo.save(userDetail);
        return new ReturnMassage<>("0", "保存成功");
    }

    @Override
    public Page<UserAuth> search(String param, String value, int pageNo, int pageSize) {
        log.info(param + "|||" + value);
        Page<UserAuth> page;
        if ("userName".equals(param)) {
            page = userAuthRepo.findAll(
                    (root, query, cb) ->
                            cb.like(root.get(param), "%" + value + "%"),
                    PageRequest.of(pageNo, pageSize));
        } else if (StringUtils.isEmpty(value)) {
            page = userAuthRepo.findAll((
                            (root, criteriaQuery, criteriaBuilder) ->
                                    criteriaBuilder.isNull(root.get(param))),
                    PageRequest.of(pageNo, pageSize));
        } else {
            page = userAuthRepo.findAll(
                    (root, query, cb) ->
                            cb.like(root.get(param), "%" + value + "%"),
                    PageRequest.of(pageNo, pageSize));
        }
        page.getContent().forEach(userAuth -> {
            String createName = userService.getUserName(userAuth.getCreaterId());
            userAuth.setCreaterId(StringUtils.isEmpty(createName) ? userAuth.getUserName() : createName);
        });
        log.info("{}", page.getContent());
        return page;
    }

    @Override
    public ReturnMassage<String> saveUser(UserAuth user) {
        userAuthRepo.save(user);
        return new ReturnMassage<>("0", "保存成功");
    }


}
