package com.novax.ex.demo1.open.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author mars
 * @description DemoQuery
 * @date 2022-12-12 11:05
 */
@Data
@Schema(description = "demo测试")
public class DemoQuery {
    @Schema(description = "name")
    private String name;
    @Schema(description = "amount")
    private BigDecimal amount;
    @Schema(description = "date")
    private Date date;
    @Schema(description = "localDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime localDateTime;
}
