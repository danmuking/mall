package com.ly.mall.service.impl;

import com.ly.mall.domain.User;
import com.ly.mall.mapper.UserMapper;
import com.ly.mall.service.RedisService;
import com.ly.mall.service.UserService;
import com.ly.mall.utils.EmailUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @projectName: mall
 * @package: com.ly.mall.service
 * @className: UserServiceImpl
 * @author: LinYi
 * @description: UserService的具体实现类
 * @date: 2023/5/4 22:59
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisService redisService;

    @Value("${base-path}")
    String BATH_PATH;

    @Override
    public User findUserByName(String username) {
        User user = userMapper.findByName(username);
        return user;
    }

    @Override
    public int insertUser(User user) {
//        对用户密码进行md5加密
        String password = user.getPassword();
        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Str);
//        插入数据库
        int result;
        try {
            result = userMapper.insert(user);
        }catch (DuplicateKeyException e){
            result = 0;
        }
        return result;
    }

    @Override
    public User userRegister(User user) {

//        检查是否有用户名相同的用户
        User user1= userMapper.findByName(user.getUsername());
        if(user1!=null){
            return null;
        }

//        插入用户
        int result = insertUser(user);
        if(result>0){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public int forgetPassword(String email) {
//       检查用户是否存在
        User user = userMapper.findByEmail(email);
        if(user == null){
            return -1;
        }
//       生成唯一url
        String sid = RandomStringUtils.randomAlphanumeric(32);
        String url = BATH_PATH+"/users/resetPassword?"+"sid="+sid+"&email="+email;

//       发送邮件
        try {
            EmailUtils.sendEmail(email,"重置你的账户","请点击连接重置你的密码："+"<a href ='" + url + "'>"+url+"</a>。"+"10分钟内有效");
        }
        catch (EmailException e){
            return -2;
        }
        redisService.set(email,sid,10*60);
        return 1;
    }

    @Override
    public int resetPassword(String email, String password, String sid) {
        if(! redisService.hasKey(email)){
            return -1;
        }
        else if(!sid.equals(redisService.get(email))){
            return -2;
        }
        redisService.del(email);
        User user = userMapper.findByEmail(email);
        String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
        if(md5Str.equals(user.getPassword())){
            return -3;
        }
        user.setPassword(md5Str);
        return userMapper.updateById(user);
    }
}
