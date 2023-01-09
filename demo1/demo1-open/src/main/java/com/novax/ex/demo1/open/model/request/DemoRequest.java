package com.novax.ex.demo1.open.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * DemoRequest
 */
@Data
@Schema(description = "demo测试")
public class DemoRequest {
    @Schema(description = "id")
    private Long id;
    @NotEmpty(message = "名字不能为空")
    @Schema(description = "名字")
    private String name;
    //    @Max(message = "年龄不能超过200", value = 200)
//    @Min(message = "年龄不能小于0", value = 0)
    @Range(min = 0L, max = 200L, message = "年龄必须在0～200之间")
    @Schema(description = "age")
    private Integer age;
    @Schema(description = "symbol")
    private String symbol;
}
