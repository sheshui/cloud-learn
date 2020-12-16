package com.neuqsoft.common.entity;/**
 * @author sunjiarui
 * @Date 2020/9/21
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserRole {
    @Id
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("角色id")
    private String roleId;
}
