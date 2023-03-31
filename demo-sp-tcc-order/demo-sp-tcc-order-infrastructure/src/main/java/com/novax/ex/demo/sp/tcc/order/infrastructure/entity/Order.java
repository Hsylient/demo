package com.novax.ex.demo.sp.tcc.order.infrastructure.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author david
 * @date 2023/03/28
 */
@Accessors(chain = true)
@Data
public class Order{
    private Long id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private BigDecimal money;
    private String status;
}
