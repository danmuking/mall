package com.lin.mall.product.service.impl;

import com.lin.mall.product.dao.AttrAttrgroupRelationDao;
import com.lin.mall.product.dao.AttrGroupDao;
import com.lin.mall.product.dao.CategoryDao;
import com.lin.mall.product.entity.AttrAttrgroupRelationEntity;
import com.lin.mall.product.entity.AttrGroupEntity;
import com.lin.mall.product.entity.CategoryEntity;
import com.lin.mall.product.service.CategoryService;
import com.lin.mall.product.vo.AttrRespVo;
import com.lin.mall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.Query;

import com.lin.mall.product.dao.AttrDao;
import com.lin.mall.product.entity.AttrEntity;
import com.lin.mall.product.service.AttrService;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Resource
    AttrAttrgroupRelationDao relationDao;
    @Resource
    AttrGroupDao attrGroupDao;
    @Resource
    CategoryDao categoryDao;
    @Resource
    CategoryService categoryService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo,attrEntity);

//        保存基本关系
        this.save(attrEntity);
//        保存关联关系
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        relationDao.insert(attrAttrgroupRelationEntity);
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId) {
        IPage<AttrEntity> page;
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>();
        String key = (String) params.get("key");
        wrapper.eq("attr_id",key).or().like("attr_name",key);

        if (catelogId != 0) {
            wrapper.eq("catelog_id", catelogId);
        }
        page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );
        PageUtils pageUtils = new PageUtils(page);

        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> respVos = records.stream().map(
                record -> {
                    AttrRespVo attrRespVo = new AttrRespVo();
                    BeanUtils.copyProperties(record, attrRespVo);

//                    获取分组名称
                    AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", record.getAttrId()));
                    if (attrAttrgroupRelationEntity != null) {
                        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                        attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                    }

//                    获取分类名称
                    CategoryEntity categoryEntity = categoryDao.selectById(record.getCatelogId());
                    if (categoryEntity != null) {
                        attrRespVo.setCatelogName(categoryEntity.getName());
                    }
                    return attrRespVo;
                }
        ).collect(Collectors.toList());

        pageUtils.setList(respVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getDetailAttr(Long attrId) {
//        获取属性基本信息
        AttrEntity attrEntity = this.getById(attrId);
//        从关系表中获取分组
        AttrAttrgroupRelationEntity relationEntity = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
//        根据分组id获取类别
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(relationEntity.getAttrGroupId());
//        获取类别路径
        Long[] catelogPath = categoryService.findCatelogPath(attrGroupEntity.getCatelogId());

        AttrRespVo attrRespVo = new AttrRespVo();
        BeanUtils.copyProperties(attrEntity,attrRespVo);
        attrRespVo.setCatelogPath(catelogPath);
        attrRespVo.setCatelogId(attrGroupEntity.getCatelogId());
        attrRespVo.setAttrGroupId(attrGroupEntity.getAttrGroupId());

        return attrRespVo;
    }

    @Override
    public void updateAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo,attrEntity);
//        修改基本信息
        this.updateById(attrEntity);
//        修改分类信息
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        BeanUtils.copyProperties(attrVo,attrAttrgroupRelationEntity);
        relationDao.update(attrAttrgroupRelationEntity,new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrEntity.getAttrId()));
//        修改分组信息
        AttrGroupEntity attrGroupEntity = new AttrGroupEntity();
        BeanUtils.copyProperties(attrVo,attrGroupEntity);
        attrGroupDao.update(attrGroupEntity,new QueryWrapper<AttrGroupEntity>().eq("attr_group_id",attrGroupEntity.getAttrGroupId()));
    }

}