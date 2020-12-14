package com.neuqsoft.hrmanage;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;

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

}
