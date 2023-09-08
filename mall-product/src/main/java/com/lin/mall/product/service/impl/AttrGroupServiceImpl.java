package com.lin.mall.product.service.impl;

import com.lin.mall.product.dao.AttrAttrgroupRelationDao;
import com.lin.mall.product.dao.AttrDao;
import com.lin.mall.product.entity.AttrAttrgroupRelationEntity;
import com.lin.mall.product.entity.AttrEntity;
import com.lin.mall.product.service.AttrService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.Query;

import com.lin.mall.product.dao.AttrGroupDao;
import com.lin.mall.product.entity.AttrGroupEntity;
import com.lin.mall.product.service.AttrGroupService;

import javax.annotation.Resource;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Resource
    AttrAttrgroupRelationDao relationDao;
    @Resource
    AttrService attrService;
    @Resource
    AttrGroupDao attrGroupDao;
    @Resource
    AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        IPage<AttrGroupEntity> page;
        String key = (String) params.get("key");
//            select * from pms_attr_group where catelogId = ? and (attr_group_id = key or attr_group_name like key)
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if (!StringUtils.isEmpty(key)){
            wrapper.and(obj -> {
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }

        if (catelogId ==0){
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
        }else {
            wrapper.eq("catelog_id", catelogId);
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
        }

        return new PageUtils(page);
    }

    @Override
    public List<AttrEntity> getRelation(Long attrgroupId) {
//        根据分组id获取关联的属性
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if(attrIds == null || attrIds.size() == 0){
            return null;
        }
        List<AttrEntity> attrEntities = attrService.listByIds(attrIds);
        return attrEntities;
    }

    @Override
    public PageUtils getNoRelation(Map<String, Object> params,Long attrgroupId) {
//        根据分组id获取关联的属性
        List<AttrAttrgroupRelationEntity> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

//        根据分组id找到对应的类别id
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
//        根据类别id获取类别下未被当前分组关联的所有属性
        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>()
                .eq("catelog_id", attrGroupEntity.getCatelogId());
//        当存在存在关联属性的时候排除
        if(entities!=null && !entities.isEmpty()){
            attrEntityQueryWrapper.notIn("attr_id", entities.stream()
                    .map(AttrAttrgroupRelationEntity::getAttrId)
                    .collect(Collectors.toList()));
        }

        IPage<AttrEntity> page = attrService.page(new Query<AttrEntity>().getPage(params), attrEntityQueryWrapper);

        PageUtils pageUtils = new PageUtils(page);

        return pageUtils;
    }


}