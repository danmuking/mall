package com.lin.mall.product.dao;

import com.lin.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
