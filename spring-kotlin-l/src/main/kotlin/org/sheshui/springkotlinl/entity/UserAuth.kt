package org.sheshui.springkotlinl.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * 用户认证表(UserAuth)实体类
 *
 * @author makejava
 * @since 2020-08-04 08:59:37
 */
//@Data //@Entity
@Table("user_auth") //@ApiModel("用户实体类")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
data class UserAuth(
    /**
     * 用户id
     */
    @Id //    @ApiModelProperty("用户主键")
    //    @GeneratedValue(generator = "jpa-uuid")
    val userId: String,

    /**
     * 用户名
     */
    //    @ApiModelProperty("用户名")
    val userName: String,

    /**
     * 用户手机号
     */
    //    @ApiModelProperty("用户手机号")
    val userPhone: String?,

    /**
     * 用户邮箱
     */
    //    @ApiModelProperty("用户邮箱")
    val userEmail: String?,

    /**
     * 用户密码
     */
    //    @ApiModelProperty("用户密码")
    val userPwd: String,

    /**
     * 用户状态
     */
    //    @ApiModelProperty("用户状态")
    val userStatus: String
)