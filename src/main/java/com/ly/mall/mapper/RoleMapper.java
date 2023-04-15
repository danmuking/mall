package com.ly.mall.mapper;

import com.ly.mall.domain.Role;
import com.ly.mall.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LinYi
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Mapper
public interface RoleMapper {
    /**
     * @param role: 角色实体
     * @return int 插入成功的条数
     * @author LinYi
     * @description 根据角色实体将角色数据插入数据库
     * @date 2023/4/15 21:53
     */
    int insert(Role role);

    /**
     * @param role: 角色实体
     * @return int 更新成功的条数
     * @author LinYi
     * @description 根据角色id更新角色数据
     * @date 2023/4/15 22:03
     */
    int updateById(Role role);

    /**
     * @param id: 角色id
     * @return Role 角色实体
     * @author LinYi
     * @description 根据角色id获取角色信息
     * @date 2023/4/15 22:11
     */
    Role findById(@Param("id") Long id);

    /**
    *  @return List<Role> 所有角色列表
     * @author LinYi
     * @description 获取所有角色信息
     * @date 2023/4/15 22:12
     */
    List<Role> getAll();

    /**
     * @param id: 角色id
     * @return int 删除成功的条数
     * @author LinYi
     * @description 根据角色id删除角色
     * @date 2023/4/15 22:13
     */
    int deleteById(@Param("id") Long id);
}
