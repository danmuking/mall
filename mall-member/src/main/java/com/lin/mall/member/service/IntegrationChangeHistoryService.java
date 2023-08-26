package com.lin.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 18:51:25
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

