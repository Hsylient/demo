package com.novax.ex.demo.sp.tcc.business.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author david
 * @date 2023/03/28
 */
@SpringBootApplication(scanBasePackages = {"com.novax.ex.common","com.novax.ex.demo.sp.tcc.business"})
@MapperScan("com.novax.ex.demo.sp.tcc.business.infrastructure")
@EnableFeignClients
public class BusinessProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProviderApplication.class, args);
    }
}
