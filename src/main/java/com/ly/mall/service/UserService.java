package com.ly.mall.service;

import com.ly.mall.domain.User;
import org.apache.commons.mail.EmailException;

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

    /**
     * @param user: 用户实体
     * @return int: 插入成功的用户数量
     * @author LinYi
     * @description 保存用户信息。
     * @date 2023/5/4 22:56
     */
    public int insertUser(User user);

    /**
     * @param user: 用户实体
     * @return User 用户信息
     * @author LinYi
     * @description 用户注册，成功返回用户实体，失败返回null
     * @date 2023/5/11 23:04
     */
    public User userRegister(User user);

    /**
     *
     * @param email 用户邮箱
     * @return 状态码
     */
    public int forgetPassword(String email);

    /**
     *
     * @param email 用户邮箱
     * @param password 用户新密码
     * @param sid 用户sid
     * @return 状态码
     */
    public int resetPassword(String email,String password,String sid);
}
