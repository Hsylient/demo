package com.novax.ex.common.option;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 可选项
 *
 * @author Deucalion@novax.email
 * @date 2022/9/25 16:05
 */
@Data
@Schema(description = "可选项")
public class OptionBoxResponse {
    @Schema(description = "数字货币选择")
    private List<OptionResponse> currency = new ArrayList<>();
    @Schema(description = "法币选择")
    private List<OptionResponse> fiat = new ArrayList<>();
    @Schema(description = "支付方式列表选择")
    private List<OptionResponse> payments = new ArrayList<>();
    @Schema(description = "申诉类型选择")
    private List<OptionResponse> appeal = new ArrayList<>();
    @Schema(description = "订单状态选择")
    private List<OptionResponse> orderStatus = new ArrayList<>();
    @Schema(description = "支付时效选择")
    private List<OptionResponse> timeLimit = new ArrayList<>();
}
