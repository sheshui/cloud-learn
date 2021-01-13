package com.neuqsoft.hrmanage.api;


import com.neuqsoft.hrmanage.dto.ReturnMassage;
import com.neuqsoft.hrmanage.dto.UserDetailDto;
import com.neuqsoft.hrmanage.entity.UserAuth;
import com.neuqsoft.hrmanage.service.UserAuthService;
import com.neuqsoft.hrmanage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunjiarui
 */
@Api(tags = "用户生成")
@RestController
@RequestMapping
public class UserSignupApi {
    @Autowired
    UserAuthService authService;

    @Autowired
    UserService userService;

    @ApiOperation("保存用户信息-注册")
    @PostMapping("/user/anonymous/signup")
    public ReturnMassage<String> saveUserAuth(@RequestBody UserAuth userAuth) {
        return authService.saveUserAuth(userAuth);
    }

    @GetMapping("/api/user/detail/info")
    public ReturnMassage<UserDetailDto> getUserDetail(@RequestParam String userId) {
        System.out.println(userId);
        return new ReturnMassage<>("0", "获取成功", userService.getUserDetail(userId));
    }
}
