package com.novax.ex.demo.open.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mars
 * @description RedisRequest
 * @date 2022-11-30 18:58
 */
@Data
@Schema(description = "redis请求")
public class RedisStringRequest {
    @Schema(description = "key")
    private String key;

    @Schema(description = "value")
    private String value;

    @Schema(description = "时间")
    private Long time;

    @Schema(description = "时间单位：NANOSECONDS、MICROSECONDS、MILLISECONDS、SECONDS、MINUTES、HOURS、DAYS",
            requiredProperties = {"NANOSECONDS", "MICROSECONDS", "MILLISECONDS", "SECONDS", "MINUTES", "HOURS", "DAYS"},
    defaultValue = "SECONDS")
    private String unit;

}
