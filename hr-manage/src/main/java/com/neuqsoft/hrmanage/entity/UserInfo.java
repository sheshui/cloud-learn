package com.neuqsoft.hrmanage.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (VUserInfo)实体类
 *
 * @author makejava
 * @since 2020-08-27 08:34:01
 */
@Data
@Entity
@Table(name = "v_user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 118458484281830864L;
    @Id
    private String userId;

    private String userName;

    private String userPhone;

    private String userEmail;

    private String userPwd;

    private String userStatus;

    private String userAvatar;

    private String userBirthday;
}