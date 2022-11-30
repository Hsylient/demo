package com.novax.ex.demo1.provider.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig
 *
 * @author zhenghao
 * @date 2022/8/11 16:33
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(info())
                .externalDocs(externalDocumentation());
    }

    private Info info() {
        return new Info()
                .title("novax demo API接口文档")
                .description("novax demo API接口文档")
                .version("1.0.0")
                .license(license());
    }

    private License license() {
        return new License()
                .name("novax 1.0")
                .url("https://dev.nova-x.ca/");
    }

    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("novax demo API接口文档")
                .url("https://dev.nova-x.ca/");
    }

    @Bean
    public GroupedOpenApi demoApi() {
        return GroupedOpenApi.builder()
                .group("demo")
                .pathsToMatch("/v1/**")
                .build();
    }
}
