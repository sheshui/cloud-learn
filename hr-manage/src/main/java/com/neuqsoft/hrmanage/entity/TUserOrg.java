package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户和部门关系(TUserOrg)实体类
 *
 * @author makejava
 * @since 2020-08-27 08:34:00
 */
@Data
@Entity
@Table(name = "t_user_org")
@ApiModel("用户和部门关系")
public class TUserOrg implements Serializable {
    private static final long serialVersionUID = -33460793281538869L;
    @Id
    @ApiModelProperty("id")
    private String id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;
    /**
     * 组织部门id
     */
    @ApiModelProperty("组织部门id")
    private String orgId;
}