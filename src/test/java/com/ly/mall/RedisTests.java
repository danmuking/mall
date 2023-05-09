package com.ly.mall;

import com.ly.mall.domain.User;
import com.ly.mall.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @projectName: mall
 * @package: com.ly.mall
 * @className: RedisTests
 * @author: LinYi
 * @description: redis测试类
 * @date: 2023/5/9 23:14
 * @version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Resource
    RedisService redisService;

    @Test
    public void testRedis() throws Exception {
        redisService.set("test","test");
        String msg = (String) redisService.get("test");
        System.out.println(msg);
        redisService.del("test");
    }
}
