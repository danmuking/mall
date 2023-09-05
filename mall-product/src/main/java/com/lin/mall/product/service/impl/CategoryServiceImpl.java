package com.lin.mall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.common.utils.PageUtils;
import com.lin.common.utils.Query;

import com.lin.mall.product.dao.CategoryDao;
import com.lin.mall.product.entity.CategoryEntity;
import com.lin.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

//        获取所有数据库记录
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);

//        找出所有顶级菜单
        List<CategoryEntity> level1Menus = categoryEntityList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid()==0)
                .map((menu)->{
                    menu.setChildren(getChildren(menu,categoryEntityList));
                    return menu;
                })
                .sorted(((o1, o2) -> (o1.getSort()==null?0:o1.getSort())-(o2.getSort()==null?0:o2.getSort())))
                .collect(Collectors.toList());

//        找出父菜单的所有子菜单

        return level1Menus;
    }

    /**
     * 逻辑删除菜单
     * @param list
     */
    @Override
    public void logicRemoveByIds(List<Long> list) {
        baseMapper.deleteBatchIds(list);
    }

    /**
     * 获取categoryEntityList中root的子菜单
     * @param root
     * @param categoryEntityList
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity root,List<CategoryEntity> categoryEntityList){
        return categoryEntityList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildren(categoryEntity,categoryEntityList));
                    return categoryEntity;
                })
                .sorted(((o1, o2) -> (o1.getSort()==null?0:o1.getSort())-(o2.getSort()==null?0:o2.getSort())))
                .collect(Collectors.toList());
    }


    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, path);
        return parentPath.toArray(new Long[0]);
    }
    private List<Long> findParentPath(Long catelogId,List<Long> path){
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),path);
        }
        path.add(catelogId);
        return path;
    }

}