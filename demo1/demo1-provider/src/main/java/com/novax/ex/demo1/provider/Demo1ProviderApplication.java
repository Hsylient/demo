package com.novax.ex.demo1.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.novax.ex.common","com.novax.ex.demo1"})
@MapperScan("com.novax.ex.demo1.infrastructure.mapper")
@EnableMongoRepositories("com.novax.ex.demo1.infrastructure.dao")
@EnableFeignClients
public class Demo1ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo1ProviderApplication.class, args);
    }

}
