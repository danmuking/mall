package com.lin.mall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @projectName: mall
 * @package: com.lin.mall.product.vo
 * @className: AttrRespVo
 * @author: LinYi
 * @description: TODO
 * @date: 2023/9/7 0:00
 * @version: 1.0
 */

@Data
public class AttrRespVo extends AttrVo{
    private String catelogName;

    private String groupName;

    private Long[] catelogPath;
}
