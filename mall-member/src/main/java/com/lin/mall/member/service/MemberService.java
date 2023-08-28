package com.lin.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 18:51:25
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
