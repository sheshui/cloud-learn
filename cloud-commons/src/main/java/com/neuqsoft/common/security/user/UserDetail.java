package com.neuqsoft.common.security.user;


import com.google.gson.Gson;
import com.neuqsoft.common.entity.UserAuth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author sunjiarui
 */
@Getter
@Setter
public class UserDetail implements UserDetails {
    /**
     * 用户id
     */
    @ApiModelProperty("用户主键")
    private String userId;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private final String userName;
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

    @ApiModelProperty("用户密码")
    private String userPwd;

    private Set<? extends GrantedAuthority> authorities;


    public UserDetail(String toJson, String encode, Collection<GrantedAuthority> authorities) {
        UserAuth userAuth = new Gson().fromJson(toJson, UserAuth.class);
        userId = userAuth.getUserId();
        userName = userAuth.getUserName();
        userPwd = userAuth.getUserPwd();
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.userPhone = userAuth.getUserPhone();
        this.userEmail = userAuth.getUserEmail();
        this.userStatus = userAuth.getUserStatus();
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                new UserDetail.AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.userPwd;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !"0".equals(this.userStatus);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !"2".equals(this.userStatus);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "1".equals(this.userStatus);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set.
            // If the authority is null, it is a custom authority and should precede
            // others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
}
