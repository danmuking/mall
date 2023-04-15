package com.ly.mall.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @projectName: mall
 * @package: com.ly.mall.domain
 * @className: Role
 * @author: LinYi
 * @description: 角色实体
 * @date: 2023/4/14 21:04
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色实体")
public class Role {
    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @NotNull
    @ApiModelProperty("角色id")
    private Long id;

    @NotNull
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色权限列表")
    private List<Action> actions;

}
