package com.neuqsoft.hrmanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author sunjiarui
 */
@Data
@ApiModel("用户信息详情")
public class UserDetailDto {
    /**
     * 用户id
     */
    @Id
    @ApiModelProperty("用户id")
    private String userId;
    /**
     * 用户手机号
     */
    @ApiModelProperty("用户手机号")
    private String userPhone;
    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String userEmail;
    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String userPwd;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userAvatar;
    /**
     * 用户生日
     */
    @ApiModelProperty("用户生日")
    private String userBirthday;
}