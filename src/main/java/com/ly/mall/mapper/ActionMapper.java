package com.ly.mall.mapper;


import com.ly.mall.domain.Action;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LinYi
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Mapper
public interface ActionMapper {
    /**
     * @param action: 权限实体
     * @return int 插入成功的条数
     * @author LinYi
     * @description 将权限插入权限表
     * @date 2023/4/20 19:33
     */
    int insert(Action action);

    /**
     * @param id: 权限id
    *  @return Action 权限实体
     * @author LinYi
     * @description 根据权限id获取权限信息
     * @date 2023/4/20 19:35
     */
    Action findById(@Param("id") Long id);

    /**
     * @param action: 权限实体
     * @return int 更新成功的条数
     * @author LinYi
     * @description 根据权限id更新权限信息
     * @date 2023/4/20 19:36
     */
    int updateById(Action action);

    /**
     * @return List<Action> 权限列表
     * @author LinYi
     * @description 获取所有权限信息
     * @date 2023/4/20 19:38
     */
    List<Action> getAll();

    /**
     * @param id: 权限id
     * @return int 删除成功的条数
     * @author LinYi
     * @description 根据权限id删除权限
     * @date 2023/4/20 19:39
     */
    int deleteById(@Param("id") Long id);
}
