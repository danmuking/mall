package com.ly.mall.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @projectName: mall
 * @package: com.ly.mall.domain
 * @className: User
 * @author: Ly
 * @description: 用户Bean
 * @date: 2023/4/9 22:41
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体")
public class User {

    public User(String username) {
        this.username = username;
    }

    public User(Long id,String username){
        this.id = id;
        this.username = username;
    }

    @NotNull
    @ApiModelProperty("用户id")
    private Long id;

    @Size(min = 2,max = 10)
    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("用户角色")
    private Role role;

//    @Size(min=8,max = 30)
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).+$")
//    @ApiModelProperty("密码")
//    private String password;
}
