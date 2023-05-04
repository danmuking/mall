package com.ly.mall.service;

import com.ly.mall.domain.User;

/**
 * @author LinYi
 * @description 用户服务接口
 * @date 2023/5/4 22:55
 */
public interface UserService {
    /**
     * @param username: 用户名
     * @return User: 用户实体，包含所有用户信息
     * @author LinYi
     * @description 根据用户名查找用户信息，成功将会返回一个包含该用户所有信息的用户实体，若失败则返回null。
     * @date 2023/5/4 22:56
     */
    public User findUserByName(String username);
}
