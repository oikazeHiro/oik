package com.oik.util.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 应用程序上下文跑龙套
 *
 * @author LEAF
 * @date 2022-09-07
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    //根据bean名字获取工厂中指定bean 对象
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 获取对象通过Class
     *
     * @param cls 类
     * @return Object
     */
    public static <C> Object getBean(Class<C> cls) throws BeansException {
        return context.getBean(cls);
    }
}
