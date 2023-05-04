package com.ly.mall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: mall
 * @package: com.ly.mall.domain
 * @className: UserAction
 * @author: LinYi
 * @description: TODO
 * @date: 2023/4/20 20:50
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAction {
    private int userId;
    private int roleId;

    @Override
    public String toString() {
        return "UserAction{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
