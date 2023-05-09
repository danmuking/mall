package com.ly.mall;

import com.ly.mall.utils.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailUtilsTests {
    @Test
    public void testUserService() throws Exception {
//        EmailUtils.sendEmail("550210817@qq.com","test","test");
        EmailUtils.sendVerificationCode("550210817@qq.com","123456");
    }
}
