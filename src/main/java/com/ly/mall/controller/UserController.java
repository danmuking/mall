package com.ly.mall.controller;

import com.ly.mall.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @projectName: mall
 * @package: com.ly.mall.web
 * @className: UserController
 * @author: Ly
 * @description: 用户Controller
 * @date: 2023/4/9 22:43
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "/users")
@Slf4j
@Api(tags = "/用户管理")
public class UserController {
    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long,User>());

    /**
     * @return List<User>
     * @author LinYi
     * @description 获取所有用户信息
     * @date 2023/4/9 22:48
     */
    @GetMapping("/")
    @ApiOperation(value = "获取用户列表")
    public List<User> getUserList(){
        List<User> users = new ArrayList<>(userMap.values());

        return users;
    }

    /**
     * @param user:用户信息
     * @return String
     * @author LinYi
     * @description 添加一个用户
     * @date 2023/4/9 22:53
     */
    @ApiOperation(value = "添加用户",notes = "根据User对象创建用户")
    @PostMapping("/")
    public String postUser(@Valid @RequestBody User user){
        userMap.put(user.getId(), user);
        return "success";
    }

    /**
     * @param id: 用户id
     * @return User
     * @author LinYi
     * @description 根据id获取用户信息
     * @date 2023/4/9 22:54
     */
    @ApiOperation(value = "获取用户",notes = "根据用户id获取用户信息")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        User user = userMap.get(id);
        return user;
    }

    /**
     * @param id: 用户id
     * @param user: 用户信息
     * @return String
     * @author LinYi
     * @description 根据用户id，修改用户信息
     * @date 2023/4/9 23:01
     */
    @ApiOperation(value = "更新用户",notes = "根据用户id，用User对象中的信息更新用户")
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id,@RequestBody User user){
        User user1 = userMap.get(id);
        user1.setUsername(user.getUsername());
//        user1.setPassword(user.getPassword());
        userMap.put(id,user1);
        return "success";
    }


    /**
     * @param id: 用户id
     * @return String
     * @author LinYi
     * @description 根据id删除用户
     * @date 2023/4/9 22:56
     */
    @ApiOperation(value = "删除用户",notes = "根据用户id删除用户")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userMap.remove(id);
        return "success";
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "根据用户名和密码登录")
    public String login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            return "success";
        } catch (UnknownAccountException e) {
            return "用户名错误";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
    }
}



