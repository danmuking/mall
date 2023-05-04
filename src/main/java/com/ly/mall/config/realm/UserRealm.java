package com.ly.mall.config.realm;


import com.ly.mall.domain.User;
import com.ly.mall.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author LinYi
 * @description 用户权限认证
 * @date 2023/5/4 22:49
 */
public class UserRealm implements Realm {
    @Resource
    UserService userService;

    /**
     * @return String
     * @author LinYi
     * @description 返回全局唯一的realm名称
     * @date 2023/5/4 22:49
     */
    @Override
    public String getName() {
        return "userRealm";
    }

    /**
     * @param authenticationToken:
     * @return boolean
     * @author LinYi
     * @description 判断当前是否支持这种类型的token
     * @date 2023/5/4 22:50
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return true;
    }

    /**
     * @param authenticationToken:
     * @return AuthenticationInfo
     * @author LinYi
     * @description 进行用户身份验证
     * @date 2023/5/4 22:52
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByName(token.getUsername());
        if(user != null){
            String password = String.valueOf(token.getPassword());
            String md5Str = DigestUtils.md5DigestAsHex(password.getBytes());
            if(md5Str.equals(user.getPassword())) {
                return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
            }
        }
        return null;
    }
}
