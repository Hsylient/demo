package com.novax.ex.demo.seata.stock.provider;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author david
 * @date 2023/03/03
 */
@EnableAutoDataSourceProxy
@SpringBootApplication(scanBasePackages = {"com.novax.ex.common","com.novax.ex.demo.seata.stock"})
@MapperScan("com.novax.ex.demo.seata.stock.infrastructure")
@EnableFeignClients
public class StockProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockProviderApplication.class, args);
    }
}
