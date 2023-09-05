package com.lin.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.lin.common.validator.ListValue;
import com.lin.common.validator.group.AddGroup;
import com.lin.common.validator.group.UpdateGroup;
import com.lin.common.validator.group.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author lin
 * @email 550210817@qq.com
 * @date 2023-08-26 17:06:49
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@NotNull(groups = {UpdateGroup.class},message = "修改必须指定品牌id")
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotNull(groups = {UpdateGroup.class,AddGroup.class},message = "品牌名必须提交")
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(groups = {AddGroup.class})
	@URL(groups = {UpdateGroup.class},message = "品牌logo必须是URL")
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(groups = {UpdateGroup.class,AddGroup.class, UpdateStatusGroup.class})
	@ListValue(vals={0,1},groups = {AddGroup.class,UpdateGroup.class, UpdateStatusGroup.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(groups={AddGroup.class})
	@Pattern(regexp="^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups={AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(groups={AddGroup.class})
	@Min(value = 0,message = "排序必须大于等于0",groups={AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
