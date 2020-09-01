package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 资源表(Resource)实体类
 *
 * @author makejava
 * @since 2020-08-27 08:34:00
 */
@Data
@Entity
@Table(name = "resource")
@ApiModel("资源表")
public class Resource implements Serializable {
    private static final long serialVersionUID = 708535123323339876L;
    /**
     * 资源id
     */
    @Id
    @ApiModelProperty("资源id")
    private String resId;
    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String resName;
    /**
     * 资源描述
     */
    @ApiModelProperty("资源描述")
    private String resDesc;
    /**
     * 父级资源id
     */
    @ApiModelProperty("父级资源id")
    private String resFather;
}