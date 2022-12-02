package com.novax.ex.demo.open.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author mars
 * @description RedisRequest
 * @date 2022-11-30 18:58
 */
@Data
@Schema(description = "redis请求 z-set")
public class RedisZSetRequest {
    @Schema(description = "key")
    private String key;

    @Schema(description = "value")
    private Object value;

    @Schema(description = "得分")
    private Double score;
}
