package com.ly.mall;

import com.ly.mall.domain.User;
import com.ly.mall.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @projectName: mall
 * @package: com.ly.mall
 * @className: UserServiceTests
 * @author: LinYi
 * @description: userService测试类
 * @date: 2023/5/4 23:07
 * @version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Resource
    UserService userService;

    @Test
    public void testUserService() throws Exception {
        User user = userService.findUserByName("test1");
//        System.out.println(user);
        user = new User();
        user.setUsername("test");
        user.setPassword("2940657509@");
        userService.insertUser(user);
        user = new User();
        user.setUsername("test1");
        user.setPassword("2940657509@");
        userService.insertUser(user);
    }


}
