package com.novax.ex.demo.provider.config.mongoConverter;

import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;

/**
 * @Description 定义写转换器
 * @Author Wade
 * @Date 12/2/22 11:34 AM
 * @Version 1.0
 */
/**
 *  Converter 这个类一定要看清楚，他是在spring的core里面的，这样才能交给容器
 **/
@WritingConverter//此注解告知spring 写数据的时候应该怎么处理
public class BigDecimalToDecimal128Converter implements Converter<BigDecimal, Decimal128> {
    @Override
    public Decimal128 convert(BigDecimal source) {
        return new Decimal128(source);//将原类型转为Decimal128 类型的
    }
}
