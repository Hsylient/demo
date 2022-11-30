package com.novax.ex.common.core.config;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;

/**
 *
 * Description: BigDecimalValueFilter
 *
 * @author shaw
 * @date 7/2/21 11:20 AM
 */
public class BigDecimalValueFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if(value instanceof BigDecimal) {
            return ((BigDecimal) value).stripTrailingZeros().toPlainString();
        }
        return value;
    }
}
