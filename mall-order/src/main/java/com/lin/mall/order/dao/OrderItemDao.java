package com.lin.mall.order.dao;

import com.lin.mall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 18:52:32
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}