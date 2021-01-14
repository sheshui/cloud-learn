package com.neuqsoft.hrmanage.service.impl;

import com.neuqsoft.hrmanage.dto.ChangePwdDto;
import com.neuqsoft.hrmanage.dto.ReturnMassage;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @Override
    public ReturnMassage<String> saveUserDetail(UserDetailDto userDetailDto) {
        UserAuth auth = userAuthRepo.findById(userDetailDto.getUserId()).get();
        auth.setUserEmail(userDetailDto.getUserEmail());
        auth.setUserPhone(userDetailDto.getUserPhone());
        userAuthRepo.save(auth);

        UserDetail detail = userDetailRepo.findById(userDetailDto.getUserId()).get();
        detail.setUserAvatar(userDetailDto.getUserAvatar());
        detail.setUserBirthday(userDetailDto.getUserBirthday());
        detail.setUserSign(userDetailDto.getUserSign());
        detail.setUserSex(userDetailDto.getUserSex());
        userDetailRepo.save(detail);
        return new ReturnMassage<>("保存成功");
    }

    @Override
    public ReturnMassage<String> changePwd(ChangePwdDto changePwdDto) {
        UserAuth userAuth = userAuthRepo.findById(changePwdDto.getUserId()).get();
        log.info(userAuth.toString());
        if (passwordEncoder.matches(changePwdDto.getOldPwd(), userAuth.getUserPwd())) {
            userAuth.setUserPwd(passwordEncoder.encode(changePwdDto.getNewPwd()));
            userAuthRepo.save(userAuth);
        } else {
            return new ReturnMassage<>("-1", "密码错误");
        }
        return new ReturnMassage<>("密码更改成功");
    }

}
