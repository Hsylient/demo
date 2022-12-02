package com.novax.ex.demo.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 测试mongo实体
 *
 * @author my.miao
 * @date 2022/6/29 18:11
 */
@Data
@Document(collection = "demo")
public class MongoEntity {
    @Id
    private Long id;

    private String name;//搜索使用

    private Integer sort;//排序使用

    private Integer age;//范围使用

    private Date date;//日期类型测试使用

    private BigDecimal money;//聚合测试使用
}
