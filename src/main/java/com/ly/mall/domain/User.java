package com.ly.mall.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    @NotNull(groups = {Update.class})
    @ApiModelProperty("用户id")
    private Long id;

    @NotNull(groups = {Save.class})
    @Size(min = 2,max = 10)
    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("用户角色")
    private List<Role> role;

    @NotNull(groups = {Save.class})
    @Size(min=8,max = 32)
    @ApiModelProperty("密码")
    private String password;

    @NotNull(groups = {Save.class})
    @Email
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 保存的时候校验分组
     */
    public interface Save {
    }

    /**
     * 更新的时候校验分组
     */
    public interface Update {
    }

}
