package com.neuqsoft.hrmanage.config.user;

import com.google.gson.Gson;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

/**
 * @author sunjiarui
 */
@Component
public class UserHolder {

    public String getUid() {
        Object details = getUserDetail();
        User user = (User) details;
        System.out.println("登录用户的信息userinfo:" + new Gson().toJson(details));
        System.out.println(user.getUsername());
        return user.getUsername();
    }

    public Object getUserDetail() {
        return ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication())
                .getUserAuthentication().getPrincipal();
    }
}
