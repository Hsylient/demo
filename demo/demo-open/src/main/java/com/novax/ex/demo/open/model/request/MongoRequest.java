package com.novax.ex.demo.open.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 测试mongo
 *
 * @author my.miao
 * @date 2022/6/29 18:11
 */
@Data
@Schema(description = "测试mongo")
public class MongoRequest {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "name")
    private String name;//搜索使用

    @Schema(description = "sort")
    private Integer sort;//排序使用

    @Schema(description = "age")
    private Integer age;//范围使用

    @Schema(description = "date")
    private Date date;//日期类型测试使用

    @Schema(description = "money")
    private BigDecimal money;//聚合测试使用
}
