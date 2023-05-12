package com.ly.mall.config;

import com.ly.mall.config.realm.UserRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LinYi
 */
@Configuration
public class ShiroConfig {
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        设置未登录时需要跳转到这个页面登录
        shiroFilterFactoryBean.setLoginUrl("/login");
//        设置登录成功后的跳转页面
        shiroFilterFactoryBean.setSuccessUrl("/success");
//        设置无权限时的跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noPermission");
//        设置访问规则
        Map<String,String> fileterChainMap = new LinkedHashMap<>();
        fileterChainMap.put("/users/login/","anon");
        fileterChainMap.put("/users/register/","anon");
        fileterChainMap.put("/users/sendCode/","anon");
        fileterChainMap.put("/users/forget/","anon");
        fileterChainMap.put("/users/resetPassword/","anon");
//        拦截其余所有页面
        fileterChainMap.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fileterChainMap);
        return shiroFilterFactoryBean;
    }
}
