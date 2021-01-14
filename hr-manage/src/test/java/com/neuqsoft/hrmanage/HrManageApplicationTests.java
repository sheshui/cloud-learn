package com.neuqsoft.hrmanage;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootTest
class HrManageApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Καλημέρα κόσμε; or こんにちは");
    }


    @Test
    void UUID() {
        System.out.println(UUID.fastUUID().toString(true));
    }

    @Test
    void encode() {
        String pwd = "9c980ec85807c1a82f581c5b5c878e83";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(pwd);
//        String pwdEny=passwordEncoder.encode(pwd);
        String pwdEny = "$2a$10$FyRLB1K5Tmy5rm/OTEwcaOchwBh3.j42WWXNwg0U919BtgAlbR6eK";
        PasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        System.out.println(pwdEny);
        System.out.println(passwordEncoder1.matches(pwd, pwdEny));

    }
}
