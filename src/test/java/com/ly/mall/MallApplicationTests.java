package com.ly.mall;

import com.ly.mall.controller.UserController;
import com.ly.mall.domain.Action;
import com.ly.mall.domain.Role;
import com.ly.mall.domain.User;
import com.ly.mall.mapper.ActionMapper;
import com.ly.mall.mapper.RoleMapper;
import com.ly.mall.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {
    private MockMvc mvc;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ActionMapper actionMapper;

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
        Assert.assertEquals(userMapper.getAll().size(),0);
        User user = new User("test");
        int i = userMapper.insert(user);
        Assert.assertEquals(i,1);
        User result = userMapper.findByName("test");
        Assert.assertEquals(result,user);
        long userId = result.getId();
        result = userMapper.findById(userId);
        Assert.assertEquals(result,user);
        Assert.assertEquals(userMapper.getAll().size(),1);
        user.setUsername("change");
        i = userMapper.updateById(user);
        Assert.assertEquals(i,1);
        result = userMapper.findById(userId);
        Assert.assertEquals(result,user);
        i = userMapper.deleteById(userId);
        Assert.assertEquals(i,1);
        Assert.assertEquals(userMapper.getAll().size(),0);
    }

    @Test
    public void testRoleMapper(){
        Assert.assertEquals(roleMapper.getAll().size(),0);
        Role role = new Role(1L,"test");
        int i = roleMapper.insert(role);
        Assert.assertEquals(i,1);
        Role result = roleMapper.findById(1L);
        Assert.assertEquals(result.getName(),role.getName());
        long roleId = result.getId();
        Assert.assertEquals(roleMapper.getAll().size(),1);
        result.setName("change");
        i = roleMapper.updateById(result);
        Assert.assertEquals(i,1);
        i = roleMapper.deleteById(roleId);
        Assert.assertEquals(i,1);
        Assert.assertEquals(roleMapper.getAll().size(),0);
    }

    @Test
    public void testActionMapper(){
        List<Action> actions = actionMapper.getAll();
        if(!actions.isEmpty()){
            for(Action action:actions){
                actionMapper.deleteById(action.getId());
            }
        }
        Assert.assertTrue(actionMapper.getAll().isEmpty());
        Action action = new Action(1L,"test");
        int i = actionMapper.insert(action);
        Assert.assertEquals(i,1);
        Action result = actionMapper.findById(1L);
        Assert.assertEquals(result.getName(),action.getName());
        long roleId = result.getId();
        Assert.assertEquals(actionMapper.getAll().size(),1);
        result.setName("change");
        i = actionMapper.updateById(result);
        Assert.assertEquals(i,1);
        i = actionMapper.deleteById(roleId);
        Assert.assertEquals(i,1);
        Assert.assertEquals(actionMapper.getAll().size(),0);

    }

}
