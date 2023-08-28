package com.lin.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 18:52:32
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
