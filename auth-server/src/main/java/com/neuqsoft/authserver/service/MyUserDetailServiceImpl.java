package com.neuqsoft.authserver.service;

import com.neuqsoft.authserver.repo.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author sunjiarui
 * @Date 2020/8/11
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserAuthRepo userAuthRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try{
//            UserAuth userAuth=Validate
//        }

        return null;
    }
}
