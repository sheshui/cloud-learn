package com.neuqsoft.hrmanage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sunjiarui
 */
@Data
@ApiModel("数据字典")
@Entity
@Table(name = "code_dictionary")
public class CodeEntity {
    @ApiModelProperty("代码类型")
    private String codeType;
    @ApiModelProperty("类型描述")
    private String typeDesc;
    @ApiModelProperty("代码值")
    private String codeValue;
    @ApiModelProperty("代码值描述")
    private String valueDesc;
    @Id
    private Integer id;


}
