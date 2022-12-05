package com.novax.ex.demo.provider.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {
    //获取容器上下文
    private static ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        this.ctx=contex;
    }
    public static ApplicationContext getContext(){
        return ctx;
    }
    public static <T> T getBean(String name){
        return (T)ctx.getBean(name);
    }
    public static String getProperty(String key){
        if(ctx == null){
            return null;
        }
        return ctx.getEnvironment().getProperty(key);
    }
    public static boolean isDemo(){
        String demo = getProperty("spring.application.name");
        return "demo".equals(demo);
    }
}
