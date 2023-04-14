package com.ly.mall;

import com.ly.mall.controller.UserController;
import com.ly.mall.domain.User;
import com.ly.mall.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.naming.Name;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {
    private MockMvc mvc;

    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }


    /**
     * @author LinYi
     * @description userController测试
     * @date 2023/4/10 22:24
     */
    @Test
    public void testUserController() throws Exception {
        RequestBuilder request;

        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        request = post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"username\":\"测试大师\",\"password\":\"abcefgHIJKLMN1@\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        request = get("/users/");
        String contentAsString = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset()); //防止中文乱码
        Assertions.assertEquals(contentAsString,"[{\"id\":1,\"username\":\"测试大师\",\"password\":\"abcefgHIJKLMN1@\"}]");

        request = put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"username\":\"测试大师\",\"password\":\"abcefgHIJKLMN1@\"}");
        contentAsString = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset()); //防止中文乱码
        Assertions.assertEquals(contentAsString,"success");

        request = get("/users/1");
        contentAsString = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset()); //防止中文乱码
        Assertions.assertEquals(contentAsString,"{\"id\":1,\"username\":\"测试大师\",\"password\":\"abcefgHIJKLMN1@\"}");

        request = delete("/users/1");
        contentAsString = mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(Charset.defaultCharset()); //防止中文乱码
        Assertions.assertEquals(contentAsString,"success");

        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

    @Test
    public void testUserMapper(){
        User user = new User("test");
        int i = userMapper.insertUser(user);
        Assert.assertEquals(i,1);
        User result = userMapper.findUserByName("test");
        Assert.assertEquals(result,user);
    }

}
