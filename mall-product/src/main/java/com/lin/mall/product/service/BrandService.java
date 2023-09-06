package com.lin.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.product.entity.BrandEntity;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 品牌
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 更新品牌，级联更新分类
     * @param brand
     */
    void updateDetail(BrandEntity brand);
}

