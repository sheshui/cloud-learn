package com.neuqsoft.hrmanage.config.user;

import com.neuqsoft.hrmanage.entity.UserAuth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * @author sunjiarui
 */
@Getter
@Setter
public class UserDetail extends User {
    /**
     * 用户id
     */
    @Id
    @ApiModelProperty("用户主键")
    @GeneratedValue(generator = "jpa-uuid")
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
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private String userStatus;

    public UserDetail(UserAuth userAuth, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userAuth.getUserId();
        this.userName = userAuth.getUserName();
        this.userPhone = userAuth.getUserPhone();
        this.userEmail = userAuth.getUserEmail();
        this.userStatus = userAuth.getUserStatus();
    }
}
