package com.qihai.commerce.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @Description: 获取bean工具类
 * @author zhugj
 * @date 2017年12月11日 下午2:35:06
 * @version 1.0.0 
 *
 */
@Service
@Lazy(false)
public class SpringContextsUtil implements ApplicationContextAware {
	
	public static String applicationName = null;
    public static String applicationAbbr = null;

    private static ApplicationContext applicationContext;    //Spring应用上下文环境
    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext
     * @throws org.springframework.beans.BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	applicationName = applicationContext.getId();
    	SpringContextsUtil.applicationContext = applicationContext;
    	applicationAbbr = getProperty("spring.application.abbr", applicationName);
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws org.springframework.beans.BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
     * @param name       bean注册名
     * @param requiredType 返回对象类型
     * @return Object 返回requiredType类型对象
     * @throws org.springframework.beans.BeansException
     */
    public static Object getBean(String name, Class requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name
     * @return boolean
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * @param name
     * @return Class 注册对象的类型
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name
     * @return
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    public static Object getBean(Class clazz) {
        return  applicationContext.getBean(clazz);
    }
    
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext != null) {
        	applicationContext.publishEvent(event);
        }
    }
    
    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    public static String getProperty(String key, String defaultValue) {
        String result = defaultValue;
        Environment environment = (Environment)getBean(Environment.class);
        if (environment != null) {
            result = environment.getProperty(key, defaultValue);
        }

        return result;
    }
}
