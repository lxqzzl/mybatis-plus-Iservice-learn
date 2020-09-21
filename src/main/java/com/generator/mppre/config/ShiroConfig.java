package com.generator.mppre.config;

import javax.servlet.Filter;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.generator.mppre.manager.SessionUtilManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * @Description shiro配置类
 * @author lxq
 * @since Sun Sep 20 18:19:07 CST 2020
 */
@Configuration
public class ShiroConfig {    

	/**
	 * 采用Session保存登录信息
	 */
	@Bean
    public SessionManager sessionManager(){  
		SessionUtilManager sessionUtilManager=new SessionUtilManager();
	    return sessionUtilManager;
    }


	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
	

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);		
      Map<String, Filter> filterMap = new HashMap<>();    
      shiroFilter.setFilters(filterMap);       
		Map<String, String> map = new LinkedHashMap<>();		
		map.put("/dologin", "anon");
		map.put("/","anon");	
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
    }	
    
	/**
	 * 采用MD5算法，散列两次
	 */
	@Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }	
	
	/**
	 * 保证实现了Shiro内部lifecycle函数的bean执行
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 启用shrio授权注解拦截方式，AOP式方法级权限检查
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator defaullifecycleBeanPostProcessortAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	
	/**
	 * 加入注解的使用，不加入这个注解不生效 使用shiro框架提供的切面类，用于创建代理对象
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}


}
