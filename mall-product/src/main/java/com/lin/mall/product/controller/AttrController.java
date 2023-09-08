package com.lin.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.lin.mall.product.entity.AttrAttrgroupRelationEntity;
import com.lin.mall.product.entity.AttrGroupEntity;
import com.lin.mall.product.service.AttrGroupService;
import com.lin.mall.product.vo.AttrRespVo;
import com.lin.mall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lin.mall.product.entity.AttrEntity;
import com.lin.mall.product.service.AttrService;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.R;

import javax.annotation.Resource;


/**
 * 商品属性
 *
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @GetMapping("base/list/{catelogId}")
    public R baseList(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId){
        PageUtils page = attrService.queryBaseAttrPage(params,catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
        public R info(@PathVariable("attrId") Long attrId){
		AttrRespVo attr = attrService.getDetailAttr(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
        public R save(@RequestBody AttrVo attr){

        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
        public R update(@RequestBody AttrVo attrVo){
//		attrService.updateById(attr);
        attrService.updateAttr(attrVo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
        public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
