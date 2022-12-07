package com.novax.ex.demo.infrastructure.dao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @Description mongodb返回查询对象
 * @Author Wade
 * @Date 12/5/22 2:47 PM
 * @Version 1.0
 */
@Data
public class MongoResult {
    private String name;//搜索使用
    private Integer age;//范围使用
    private BigDecimal money;//聚合测试使用

    private Integer count; //总数量

    private Set<Long> ids;//id集合
}
