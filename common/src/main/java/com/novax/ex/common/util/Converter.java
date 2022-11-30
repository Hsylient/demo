package com.novax.ex.common.util;

/**
 * @Description: bean属性拷贝转换器接口
 * @since version1.0
 */
public interface Converter<S, T> {

    /**
     * bean属性拷贝转换器接口
     *
     * @param s
     * @return
     */
    T convert(S s);

}
