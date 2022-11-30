package com.novax.ex.demo.open.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DemoRequest
 */
@Data
@Schema(description = "demo测试")
public class DemoRequest {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "名字")
    private String name;
    @Schema(description = "age")
    private Integer age;
    @Schema(description = "symbol")
    private String symbol;
}
