package com.neuqsoft.hrmanage.api;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "save")
    public ReturnMassage<String> saveUserAuth(@RequestBody UserAuth userAuth) {
        return authService.saveUserAuth(userAuth);
    }


    @ApiOperation("配置中心验证")
    @GetMapping("/config")
    public ReturnMassage<String> getConfig() {
        return new ReturnMassage<>("0", "成功", userId);
    }

    @ApiOperation("访问的端口")
    @GetMapping("/port")
    public ReturnMassage<String> getServerPort() {
        return new ReturnMassage<>("0", "成功", serverPort);
    }
}
