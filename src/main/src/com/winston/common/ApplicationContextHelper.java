package com.winston.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: 于新泽
 * @Date: Created in 22:15 2018/9/16.
 * @site :获取 spring 上下文
 */

@Component("ApplicationContextHelper")//这里希望被spring来管理
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * @param context
     * @throws BeansException
     * spring 在启动的时候会把 context 注入到 ApplicationContext 里面
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 从 applicationContext 来取 spring 的上下 bean
     */
    public static <T> T popBean(Class<T> class1){
        if(applicationContext == null ){
            return null;
        }
        return applicationContext.getBean(class1);
    }

    public static <T> T popBean(String name , Class<T> class1){
        if(applicationContext == null){
            return null;
        }
        return applicationContext.getBean(name, class1);
    }
}
