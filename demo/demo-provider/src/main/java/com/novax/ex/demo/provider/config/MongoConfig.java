package com.novax.ex.demo.provider.config;

import com.novax.ex.demo.provider.config.mongoConverter.BigDecimalToDecimal128Converter;
import com.novax.ex.demo.provider.config.mongoConverter.Decimal128ToBigDecimalConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 去除MongoDB储存数据中的_class
 *
 * @author my.miao
 * @date 2022/7/1 10:28
 */
@Configuration
public class MongoConfig {

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            //mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
            List<Converter<?, ?>> converterList = new ArrayList<>();
            converterList.add(new BigDecimalToDecimal128Converter());//添加写转换器
            converterList.add(new Decimal128ToBigDecimalConverter());//添加读转换器
            mappingConverter.setCustomConversions(new MongoCustomConversions(converterList));
        } catch (NoSuchBeanDefinitionException ignore) {
            throw ignore;
        }
        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return mappingConverter;
    }
}
