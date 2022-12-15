package com.novax.ex.demo1.open.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mars
 * @description DemoQuery
 * @date 2022-12-12 11:05
 */
@Data
@Schema(description = "demo测试2")
public class DemoQuery2 {
    @Schema(description = "name")
    private String name;
    @Schema(description = "amount")
    private BigDecimal amount;
    @Schema(description = "date")
    private Long date;
    @Schema(description = "localDateTime")
    private Long localDateTime;
}
