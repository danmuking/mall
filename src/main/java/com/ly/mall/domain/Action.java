package com.ly.mall.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @projectName: mall
 * @package: com.ly.mall.domain
 * @className: Action
 * @author: LinYi
 * @description: 权限实体
 * @date: 2023/4/14 21:02
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("权限实体")
public class Action {

    @NotNull
    @ApiModelProperty("权限id")
    private Long id;

    @NotNull
    @ApiModelProperty("权限名称")
    private String name;
}
