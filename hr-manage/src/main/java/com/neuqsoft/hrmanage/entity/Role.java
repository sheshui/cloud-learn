package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色信息表(Role)实体类
 *
 * @author makejava
 * @since 2020-08-27 08:34:00
 */
@Data
@Entity
@Table(name = "role")
@ApiModel("角色信息表")
public class Role implements Serializable {
    private static final long serialVersionUID = -70780839419382789L;
    /**
     * 角色主键
     */
    @Id
    @ApiModelProperty("角色主键")
    private String roleId;
    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String roleName;
    /**
     * 角色描述
     */
    @ApiModelProperty("角色描述")
    private String roleDesc;
    /**
     * 父角色id
     */
    @ApiModelProperty("父角色id")
    private String roleFather;
    /**
     * 角色状态
     */
    @ApiModelProperty("角色状态")
    private String roleState;
}