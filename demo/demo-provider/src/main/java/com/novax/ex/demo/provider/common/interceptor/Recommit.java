package com.novax.ex.demo.provider.common.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Recommit {

    /**
     * 参与重复提交计算的请求参数索引集合,逗号分隔
     * 例: 第1,2个参数则设置为"1,2",全部参数则用默认值即可
     * @return
     */
    String ps() default "";

    /**
     * 周期内参数相同的提交判定为重复提交
     * 单位: 秒
     * @return
     */
    int ttl() default 1;

    /**
     * 请求完是否删除key
     */
    boolean del() default false;

}
