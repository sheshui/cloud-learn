package com.neuqsoft.hrmanage.config.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * @author sunjiarui
 */
@Component
public class UserHolder {

    public String getUid() {
        LinkedHashMap<String, Object> details = getUserDetail();
        return details.get("userId").toString();
    }

    public LinkedHashMap<String, Object> getUserDetail() {
        return (LinkedHashMap<String, Object>)
                ((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication())
                        .getUserAuthentication().getDetails();
    }
}
