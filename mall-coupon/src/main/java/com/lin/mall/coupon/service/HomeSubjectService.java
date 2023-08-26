package com.lin.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 18:45:38
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

