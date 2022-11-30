package com.novax.ex.common.option;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Description: 选择器
 *
 * @author Deucalion@novax.email
 * @date 2022/9/25 16:35
 */
@Data
@Schema(description = "选择器")
public class OptionResponse {
    @Schema(description = "键")
    private String key;
    @Schema(description = "值")
    private String value;
    @Schema(description = "其他内容")
    private String extra;

    public OptionResponse(){}
    public OptionResponse(String key, String value, String extra){
        this.key=key;
        this.value=value;
        this.extra=extra;
    }
}

