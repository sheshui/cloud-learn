package com.neuqsoft.hrmanage.api;

import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Api(tags = "用户生成")
@RestController
@RequestMapping("/api/user")
public class UserAuthApi {
    @Autowired
    UserAuthService authService;

    @ApiOperation("保存用户信息")
//    @ApiImplicitParam(name = "userAuth",value = "用户信息实体",required = true,paramType = "body")
    @PostMapping(value = "save")
    public ReturnMassage<String> saveUserAuth(@RequestBody UserAuth userAuth) {
        return authService.saveUserAuth(userAuth);
    }
}
