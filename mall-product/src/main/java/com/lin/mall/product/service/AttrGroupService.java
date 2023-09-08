package com.lin.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.product.entity.AttrEntity;
import com.lin.mall.product.entity.AttrGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    /**
     * 根据分组id获取关联的所有相关属性
     *
     * @param attrgroupId
     * @return
     */
    List<AttrEntity> getRelation(Long attrgroupId);


    /**
     * 根据分组id获取当前分类下未被当前分组关联的属性
     * @param attrgroupId
     * @return
     */
    PageUtils getNoRelation(Map<String, Object> params,Long attrgroupId);
}

