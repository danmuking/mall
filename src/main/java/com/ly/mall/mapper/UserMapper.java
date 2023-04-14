package com.ly.mall.mapper;

import com.ly.mall.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LinYi
 */@Mapper
public interface UserMapper {
    /**
     * @param username: 用户名
     * @return User:用户实体
     * @author LinYi
     * @description 根据用户名查找用户信息。
     * @date 2023/4/14 21:13
     */
    User findUserByName(@Param("username") String username);

    /**
     * @param user: 用户实体
     * @return int 成功插入数据的条数
     * @author LinYi
     * @description 将用户信息插入数据库
     * @date 2023/4/14 21:35
     */
    int insertUser(User user);
}
