package com.novax.ex.demo.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.novax.ex.common","com.novax.ex.demo"})
@MapperScan("com.novax.ex.demo.infrastructure")
@EnableMongoRepositories("com.novax.ex.demo.infrastructure.dao")
//@EnableElasticsearchRepositories("com.novax.ex.demo.infrastructure.dao")
@EnableFeignClients
public class DemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProviderApplication.class, args);
    }

}
