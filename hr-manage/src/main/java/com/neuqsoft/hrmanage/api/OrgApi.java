package com.neuqsoft.hrmanage.api;

import com.neuqsoft.hrmanage.entity.Org;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunjiarui
 * @Date 2020/9/2
 */
@Api
@RestController
@RequestMapping("/api/org")
public class OrgApi {
    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public Org getAllOrg() {
        return null;
    }
}
