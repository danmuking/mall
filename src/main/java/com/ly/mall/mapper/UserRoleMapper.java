package com.ly.mall.mapper;

import com.ly.mall.domain.Action;
import com.ly.mall.domain.Role;
import com.ly.mall.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LinYi
 */
@Mapper
public interface UserRoleMapper {
    /**
     * @param userId: 用户id
     * @param roleId：角色id
     * @return int 插入成功的条数
     * @author LinYi
     * @description 将用户和角色的关系插入数据库
     * @date 2023/4/20 19:33
     */
    int insert(@Param("userId") Long userId,@Param("roleId") Long roleId);

    /**
     * @param id: 用户id
     * @return User 用户实体
     * @author LinYi
     * @description 用户id获取用户角色信息
     * @date 2023/4/20 19:35
     */
    Action findById(@Param("id") Long id);

    /**
     * @param user: 用户实体
     * @return int 更新成功的条数
     * @author LinYi
     * @description 根据用户信息更新用户角色信息
     * @date 2023/4/20 19:36
     */
    int updateById(User user);

    /**
     * @return List<Action> 权限列表
     * @author LinYi
     * @description 获取所有用户角色信息
     * @date 2023/4/20 19:38
     */
    List<Action> getAll();

    /**
     * @param user: 角色实体
     * @return int 删除成功的条数
     * @author LinYi
     * @description 根据用户和角色删除用户角色信息
     * @date 2023/4/20 19:39
     */
    int deleteById(User user, Role role);
}
