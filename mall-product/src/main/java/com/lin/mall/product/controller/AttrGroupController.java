package com.lin.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lin.mall.product.dao.AttrAttrgroupRelationDao;
import com.lin.mall.product.entity.AttrAttrgroupRelationEntity;
import com.lin.mall.product.entity.AttrEntity;
import com.lin.mall.product.service.AttrAttrgroupRelationService;
import com.lin.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lin.mall.product.entity.AttrGroupEntity;
import com.lin.mall.product.service.AttrGroupService;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.R;

import javax.annotation.Resource;


/**
 * 属性分组
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Resource
    private AttrAttrgroupRelationDao relationDao;

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable Long catelogId){
        PageUtils page = attrGroupService.queryPage(params,catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
    AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
    attrGroup.setCatelogPath(categoryService.findCatelogPath(attrGroup.getCatelogId()));

    return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
        public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
        public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    /**
     * 获取指定分组关联的所有属性
     * @param attrgroupId
     * @return
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R relation(@PathVariable Long attrgroupId){
        List<AttrEntity> attrEntities = attrGroupService.getRelation(attrgroupId);
        return R.ok().put("data",attrEntities);
    }

    /**
     * 获取当前分类下还未与当前分组关联的属性
     * @param attrgroupId
     * @return
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R noRelation(@RequestParam Map<String, Object> params,
                        @PathVariable Long attrgroupId){
        PageUtils noRelation = attrGroupService.getNoRelation(params, attrgroupId);
        return R.ok().put("page",noRelation);
    }

    @PostMapping("/attr/relation")
    public R insert(@RequestBody List<AttrAttrgroupRelationEntity> attrAttrgroupRelation){
        attrAttrgroupRelationService.saveBatch(attrAttrgroupRelation);
        return R.ok();
    }

    @PostMapping("/attr/relation/delete")
    public R delete(@RequestBody AttrAttrgroupRelationEntity[] attrAttrgroupRelation){
        relationDao.deleteBatch(Arrays.asList(attrAttrgroupRelation));
        return R.ok();
    }

}
