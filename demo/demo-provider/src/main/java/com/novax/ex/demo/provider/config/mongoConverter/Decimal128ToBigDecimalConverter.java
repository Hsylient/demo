package com.novax.ex.demo.provider.config.mongoConverter;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;

/**
 * @Description 定义读转换器
 * @Author Wade
 * @Date 12/2/22 11:40 AM
 * @Version 1.0
 */
@ReadingConverter//此注解告知spring在读取数据时应该怎么处理
public class Decimal128ToBigDecimalConverter implements Converter<Decimal128, BigDecimal> {
    @Override
    public BigDecimal convert(Decimal128 source) {
        return source.bigDecimalValue();
    }
}
