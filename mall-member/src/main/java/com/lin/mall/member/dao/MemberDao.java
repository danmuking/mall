package com.lin.mall.member.dao;

import com.lin.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 18:51:25
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
