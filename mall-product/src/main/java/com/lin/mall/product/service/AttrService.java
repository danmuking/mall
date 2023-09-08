package com.lin.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.common.utils.PageUtils;
import com.lin.mall.product.entity.AttrEntity;
import com.lin.mall.product.vo.AttrRespVo;
import com.lin.mall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存规格参数/销售属性信息
     * @param attrVo
     */
    void saveAttr(AttrVo attrVo);

    /**
     * 获取全部规格参数/销售属性信息，或根据规格参数/销售属性ID获取信息
     * @param params
     * @param catelogId
     * @param attrType
     * @return
     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    /**
     * 根据attrId获取规格参数/销售属性的详细信息
     * @param attrId
     * @return
     */
    AttrRespVo getDetailAttr(Long attrId);

    /**
     * 更新属性
     * @param attrVo
     */
    void updateAttr(AttrVo attrVo);
}

