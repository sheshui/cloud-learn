package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户认证表(UserAuth)实体类
 *
 * @author makejava
 * @since 2020-08-04 08:59:37
 */
@Data
@Entity
@Table(name = "user_auth")
@ApiModel("用户实体类")
public class UserAuth implements Serializable {
    private static final long serialVersionUID = -37766015161026030L;
    /**
     * 用户id
     */
    @Id
    @ApiModelProperty("用户主键")
    private String userId;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;
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
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private String userStatus;

    @ApiModelProperty("创建人")
    private String createrId;

    @ApiModelProperty("创建时间")
    private String createTime;

}