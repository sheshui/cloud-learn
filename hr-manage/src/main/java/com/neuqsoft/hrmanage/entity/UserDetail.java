package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户信息详情(UserDetail)实体类
 *
 * @author makejava
 * @since 2020-08-27 08:34:01
 */
@Data
@Entity
@Table(name = "UserDetail")
@ApiModel("用户信息详情")
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 130674160081464082L;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;
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