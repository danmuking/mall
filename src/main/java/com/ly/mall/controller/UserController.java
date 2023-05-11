package com.ly.mall.controller;

import com.ly.mall.domain.User;
import com.ly.mall.service.RedisService;
import com.ly.mall.service.UserService;
import com.ly.mall.utils.CommonResult;
import com.ly.mall.utils.EmailUtils;
import com.ly.mall.utils.ResultCode;
import com.ly.mall.utils.VerificationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.mail.EmailException;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    UserService userService;

    @Resource
    RedisService redisService;

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

    /**
     *
     * @param user 用户信息，必须包括用户名和密码
     * @return 登录提示信息
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "根据用户名和密码登录")
    public CommonResult login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            return CommonResult.success("登录成功");
        } catch (UnknownAccountException|IncorrectCredentialsException exception) {
            return CommonResult.validate_failed("用户名或密码错误");
        }
    }

    /**
     * 退出登录
     */
    @RequestMapping("/loginOut")
    @ApiOperation(value = "退出登录",notes = "退出当前用户登录")
    public CommonResult loginOut(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return CommonResult.success("退出登录成功");
    }
    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "校验用户参数，注册新用户")
    public CommonResult<String> register(@RequestParam String code,@RequestBody @Validated(User.Save.class) User user){
//        校验验证码是否正确
        String email = user.getEmail();
//        没有验证码或验证码不正确
        String verificationCode = (String) redisService.get(email);
        if(!redisService.hasKey(email)||!verificationCode.equals(code)){
            return CommonResult.failed("验证码不正确");
        }
        redisService.del(email);

        user = userService.userRegister(user);
        if(user == null){
            return CommonResult.failed("用户注册失败");
        }
        return CommonResult.success("用户注册成功");
    }
    @RequestMapping("/sendCode")
    @ApiOperation(value = "验证码发送",notes = "通过邮箱发送验证码")
    public CommonResult<String> sendCode(@RequestParam String email) {
        String code = VerificationUtils.generateVerificationCode();
//        将验证码保存到redis，设置过期时间为10分钟
        redisService.set(email,code,10*60);
        try {
            EmailUtils.sendVerificationCode(email,code);
        }catch (EmailException e){
            redisService.del(email);
            return CommonResult.failed("验证码发送失败");
        }
        return CommonResult.success("验证码发送成功");
    }

}



