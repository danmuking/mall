package com.ly.mall.service.impl;

import com.ly.mall.domain.User;
import com.ly.mall.mapper.UserMapper;
import com.ly.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
