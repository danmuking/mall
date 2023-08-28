package com.lin.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 19:14:40
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
