package com.ly.mall;

import com.ly.mall.utils.EmailUtils;
import org.junit.Test;

public class EmailUtilsTests {
    @Test
    public void testUserService() throws Exception {
        EmailUtils.sendEmail("550210817@qq.com","test","test");
    }
}
