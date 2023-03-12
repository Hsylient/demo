package com.novax.ex.demo.seata.business.infrastructure.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author david
 * @date 2023/03/03
 */
@Accessors(chain = true)
@Data
public class Order {
    private Integer id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private BigDecimal money;
}
