package com.neuqsoft.hrmanage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sunjiarui
 * @Date 2020/8/4
 */
@Data
@ApiModel("返回实体类")
public class ReturnMassage<T> {
    @ApiModelProperty("返回代码")
    private String code;
    @ApiModelProperty("返回的提示信息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;

    public ReturnMassage(String msg) {
        this.code = "0";
        this.msg = msg;
    }

    public ReturnMassage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnMassage(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
