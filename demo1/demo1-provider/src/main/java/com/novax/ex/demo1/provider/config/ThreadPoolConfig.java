package com.novax.ex.demo1.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 线程池大小
 *
 * @Author: my.miao
 * @Date: 2021/8/31 17:47
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService getThreadPool(){
        return Executors.newFixedThreadPool(8);
    }
}
