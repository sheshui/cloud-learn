package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Org)实体类
 *
 * @author makejava
 * @since 2020-08-27 08:33:53
 */
@Data
@Entity
@Table(name = "Org")
public class Org implements Serializable {
    private static final long serialVersionUID = -51341981485036472L;
    /**
     * 组织id
     */
    @Id
    @ApiModelProperty("组织id")
    private String orgId;
    /**
     * 组织名
     */
    @ApiModelProperty("组织名")
    private String orgName;
    /**
     * 组织描述
     */
    @ApiModelProperty("组织描述")
    private String orgDesc;
    /**
     * 父组织id
     */
    @ApiModelProperty("父组织id")
    private String fatherId;
}