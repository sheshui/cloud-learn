package com.neuqsoft.hrmanage.api;

import com.neuqsoft.hrmanage.config.user.UserHolder;
import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Api(tags = "用户生成")
@RestController
@RequestMapping("/api/user")
public class UserAuthApi {
//    @Value("${user.id}")
//    private String userId;
//    @Value("${server.port}")
//    private String serverPort;
@Autowired
UserAuthService authService;
    @Autowired
    UserHolder userHolder;

    @ApiOperation("保存用户信息")
    @PostMapping("/add")
    public ReturnMassage<String> saveUserAuth(@RequestBody UserAuth userAuth) {
        return authService.saveUserAuth(userAuth);
    }

    @GetMapping("/manage/batch")
    public ReturnMassage<String> saveUserAuths(@RequestBody List<UserAuth> userAuthList) {
        return authService.saveUserAuths(userAuthList);
    }

    @PostMapping("/manage/batch/file")
    public ReturnMassage<String> saveUserAuths(@RequestBody MultipartFile file) throws IOException {
        return authService.saveUserAuths(file);
    }

    @GetMapping("/manage/all")
    public Page<UserAuth> findAll(int pageSize, int pageNo) {
        return authService.findAll(pageSize, pageNo);
    }


    @PostMapping("/manage/del")
    public ReturnMassage<String> delUser(@RequestBody List<String> userIds) {
        return authService.delUser(userIds);
    }

    @PostMapping("/del")
    public ReturnMassage<String> delSelf(@RequestBody String userId) {
        return authService.delUser(Collections.singletonList(userId));
    }

    @PostMapping("/detail")
    public ReturnMassage<String> saveDetails(UserDetailDto detailDto) {
        return authService.saveUserDetail(detailDto);
    }

    @GetMapping("/manage/search")
    public Page<UserAuth> search(@RequestParam(defaultValue = "userName") String params,
                                 @RequestParam(required = false) String value,
                                 @RequestParam(defaultValue = "0") int pageNo,
                                 @RequestParam(defaultValue = "10") int pageSize) {
        return authService.search(params, value, pageNo, pageSize);
    }

    @GetMapping("/auth")
    public String authInfo() {
        return userHolder.getUserDetail().toString();
    }


}
