package com.neuqsoft.hrmanage.service.impl;

import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.entity.UserDetail;
import com.neuqsoft.hrmanage.repo.UserAuthRepo;
import com.neuqsoft.hrmanage.repo.UserDetailRepo;
import com.neuqsoft.hrmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author sunjiarui
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserAuthRepo userAuthRepo;

    @Autowired
    UserDetailRepo userDetailRepo;

    /**
     * 查找用户名 -由创建者id查询创建人
     *
     * @param userId 用户id
     * @return
     */
    @Override
    @Cacheable(cacheNames = "userList", cacheManager = "Cache1h", key = "#userId")
    public String getUserName(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return userId;
        }
        Optional<UserAuth> userAuth = userAuthRepo.findById(userId);
        if (userAuth.isPresent()) {
            return userAuth.get().getUserName();
        }
        return userId;
    }

    @Override
    public UserDetailDto getUserDetail(String userId) {
        UserDetailDto userDetailDto = new UserDetailDto();

        UserDetail userDetail = userDetailRepo.findById(userId).get();
        log.info(userDetail.toString());
        BeanUtils.copyProperties(userDetail, userDetailDto);
        UserAuth userAuth = userAuthRepo.findById(userId).get();
        BeanUtils.copyProperties(userAuth, userDetailDto);

        return userDetailDto;
    }

}
