package com.novax.ex.demo1.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description: 测试mongo
 *
 * @author my.miao
 * @date 2022/6/29 18:11
 */
@Data
@Document(collection = "symbol")
public class MongoEntity {

    @Id
    private Long id;

    private String symbol;

    private String currency;

    private Integer type;
}
