package com.novax.ex.demo.open.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Description: 测试mongo
 *
 * @author my.miao
 * @date 2022/6/29 18:11
 */
@Data
@Schema(description = "测试mongo")
public class MongoRepose {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "交易市场")
    private String symbol;

    @Schema(description = "币种")
    private String currency;

    @Schema(description = "账户类型1:全逐仓2:平台币3:分仓")
    private Integer type;
}
