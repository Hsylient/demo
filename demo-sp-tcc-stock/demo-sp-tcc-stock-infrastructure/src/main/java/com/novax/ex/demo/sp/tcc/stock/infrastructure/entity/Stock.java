package com.novax.ex.demo.sp.tcc.stock.infrastructure.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author david
 * @date 2023/03/28
 */
@Accessors(chain = true)
@Data
public class Stock{
    private Integer id;
    private String commodityCode;
    private Long count;
    private Long preDeductCount;
}
