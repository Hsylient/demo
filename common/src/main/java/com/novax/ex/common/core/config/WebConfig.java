package com.novax.ex.common.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * 注解配置类
 *
 * @author Administrator
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("favicon.ico").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public FilterRegistrationBean<FormContentFilter> testFilterRegistration2() {
        FilterRegistrationBean<FormContentFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FormContentFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("FormContentFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }

    /**
     * 替换fastjson解析
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1.构建了一个HttpMessageConverter  FastJson   消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.定义一个配置，设置编码方式，和格式化的形式
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //3、配置自定义解析格式
        fastJsonConfig.setSerializeFilters(new BigDecimalValueFilter());
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteBigDecimalAsPlain);
//        fastJsonConfig.setDateFormat("yyyy-MM-dd hh:mm:ss");
//        //3.设置成了PrettyFormat格式
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //4.处理中文乱码问题
        List<MediaType> fastMediaTypes =  new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
        //5.将fastJsonConfig加到消息转换器中
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
}
