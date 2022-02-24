package com.study.onlineexam.config;

import com.study.onlineexam.filter.JwtAuthcFilter;
import com.study.onlineexam.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    /**
     * @Description 自定义拦截器定义
     */
    private Map<String, Filter> filters() {
        Map<String, Filter> map = new HashMap<String, Filter>();
        map.put("jwt-authc", new JwtAuthcFilter(jwtTokenManager));
//        map.put("jwt-perms", new JwtPermsFilter());
//        map.put("jwt-roles", new JwtRolesFilter());
        return map;
    }

    /**
     * 负责拦截所有请求
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //使自定义拦截器生效
        shiroFilterFactoryBean.setFilters(filters());
        //配置系统受限资源
        //配置系统公共系统
        return shiroFilterFactoryBean;
    }

    /**
     * 创建安全管理器
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建自定义realm
     * @return
     */
    @Bean
    public Realm getRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        //开启缓存管理
        shiroRealm.setCacheManager(new com.study.onlineexam.shiro.cache.RedisCacheManager());
        shiroRealm.setCachingEnabled(true);//全局缓存
        shiroRealm.setAuthenticationCachingEnabled(true);//认证缓存
        shiroRealm.setAuthenticationCacheName("authenticationCache");
        shiroRealm.setAuthorizationCachingEnabled(true);//授权缓存
        shiroRealm.setAuthorizationCacheName("authorizationCache");
        return shiroRealm;
    }
}
