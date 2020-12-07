//package com.neuqsoft.gateway.service;
//
//import cn.hutool.core.lang.Validator;
//import com.neuqsoft.gateway.entity.UserAuth;
//import com.neuqsoft.gateway.entity.UserRole;
//import com.neuqsoft.gateway.repo.UserAuthRepo;
//import com.neuqsoft.gateway.repo.UserRoleRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author sunjiarui
// * @Date 2020/8/11
// */
//@Slf4j
//@Service
//public class MyUserDetailServiceImpl implements ReactiveUserDetailsService {
//
//    @Autowired
//    UserAuthRepo userAuthRepo;
//    @Autowired
//    UserRoleRepo userRoleRepo;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public Mono<UserDetails> findByUsername(String username) throws UsernameNotFoundException {
//        log.info("@#@#@#@#@#{}|开始认证登录@#@#@#@#@#", username);
//        UserAuth auth;
//        if (Validator.isEmail(username)) {
//            log.info("邮箱登录中...");
//            auth = userAuthRepo.findByUserEmail(username);
//        } else if (Validator.isMobile(username)) {
//            log.info("手机号登录中...");
//            auth = userAuthRepo.findByUserPhone(username);
//        } else {
//            log.info("用户名登录中...");
//            auth = userAuthRepo.findByUserName(username);
//        }
//        if (auth == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        } else {
//            List<UserRole> roleList = userRoleRepo.findByUserId(auth.getUserId());
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            roleList.forEach(userRole -> {
//                authorities.add(new SimpleGrantedAuthority(userRole.getRoleId()));
//            });
//
//            return Mono.just(User
//                            .withUsername(auth.getUserId())
//                            .password(passwordEncoder.encode(auth.getUserPwd()))
//                            .authorities(authorities)
//                            .build());
////            new auth.getUserId(), passwordEncoder.encode(auth.getUserPwd()), authorities));
//        }
//    }
//}
