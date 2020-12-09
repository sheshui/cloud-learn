package com.neuqsoft.hrmanage.api;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Api(tags = "用户生成")
@RestController
@RequestMapping("/api/user")
public class UserAuthApi {
    @Value("${user.id}")
    private String userId;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    UserAuthService authService;

    @ApiOperation("保存用户信息")
//    @ApiImplicitParam(name = "userAuth",value = "用户信息实体",required = true,paramType = "body")
    @PostMapping("/save")
    public ReturnMassage<String> saveUserAuth(@RequestBody UserAuth userAuth) {
        return authService.saveUserAuth(userAuth);
    }

    @GetMapping("/batch")
    public ReturnMassage<String> saveUserAuths(@RequestBody List<UserAuth> userAuthList) {
        return authService.saveUserAuths(userAuthList);
    }

    @PostMapping("/batch/file")
    public ReturnMassage<String> saveUserAuths(@RequestBody MultipartFile file) throws IOException {
        return authService.saveUserAuths(file);
    }

    @GetMapping("/all")
    public Page<UserAuth> findAll(int pageSize, int pageNo) {
        return authService.findAll(pageSize, pageNo);
    }


    @PostMapping("/del")
    public ReturnMassage<String> delUser(@RequestBody List<String> userIds) {
        return authService.delUser(userIds);
    }

    @PostMapping("/detail")
    public ReturnMassage<String> saveDetails(UserDetailDto detailDto) {
        return authService.saveUserDetail(detailDto);
    }


//
//    @ApiOperation("用户注册")
//    @PostMapping("/anonymous/register")
//    public ReturnMassage<String> register(@RequestBody UserAuth userAuth){
//        return authService.saveUserAuth(userAuth);
//    }


//    @ApiOperation("配置中心验证")
//    @GetMapping("/config")
//    public ReturnMassage<String> getConfig() {
//        return new ReturnMassage<>("0", "成功", userId);
//    }
//
//    @ApiOperation("访问的端口")
//    @GetMapping("/port")
//    public ReturnMassage getServerPort() {
//        return new ReturnMassage("0", "成功", serverPort);
//    }
}
