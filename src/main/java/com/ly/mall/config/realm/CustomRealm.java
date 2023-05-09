package com.ly.mall.config.realm;

import com.ly.mall.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @projectName: mall
 * @package: com.ly.mall.config.realm
 * @className: CustomRealm
 * @author: LinYi
 * @description: 实现自定义realm
 * @date: 2023/4/21 15:07
 * @version: 1.0
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    /**
     * @param authenticationToken:
     * @return AuthenticationInfo
     * @author LinYi
     * @description 实现身份认证信息
     * @date 2023/4/21 15:11
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
