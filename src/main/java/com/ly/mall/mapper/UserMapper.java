package com.ly.mall.mapper;

import com.ly.mall.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LinYi
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Mapper
public interface UserMapper {
    /**
     * @param username: 用户名
     * @return User:用户实体
     * @author LinYi
     * @description 根据用户名查找用户信息。
     * @date 2023/4/14 21:13
     */
    User findByName(@Param("username") String username);
    /**
     * @param id: 用户id
     * @return User
     * @author LinYi
     * @description 根据id查找用户
     * @date 2023/4/14 22:12
     */
    User findById(@Param("id") Long id);

    /**
     * @return List<User> 包含所有用户信息的列表
     * @author LinYi
     * @description 获取所有用户
     * @date 2023/4/14 22:13
     */
    List<User> getAll();

    /**
     * @param user: 用户实体
     * @return int 成功插入数据的条数
     * @author LinYi
     * @description 将用户信息插入数据库
     * @date 2023/4/14 21:35
     */
    int insert(User user);

    /**
     * @param user: 用户实体
     * @return int
     * @author LinYi
     * @description 根据id更新用户信息
     * @date 2023/4/14 22:16
     */
    int updateById(User user);
    /**
     * @param id: 用户id
     * @return int
     * @author LinYi
     * @description 根据id删除用户
     * @date 2023/4/14 22:15
     */
    int deleteById(@Param("id") Long id);

}
