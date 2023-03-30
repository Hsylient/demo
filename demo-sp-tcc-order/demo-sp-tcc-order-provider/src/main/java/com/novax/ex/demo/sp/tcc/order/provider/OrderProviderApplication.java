package com.novax.ex.demo.sp.tcc.order.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author david
 * @date 2023/03/28
 */
@SpringBootApplication(scanBasePackages = {"com.novax.ex.common","com.novax.ex.demo.sp.tcc.order"})
@MapperScan("com.novax.ex.demo.sp.tcc.order.infrastructure")
@EnableFeignClients
public class OrderProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderProviderApplication.class, args);
    }
}
