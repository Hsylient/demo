package com.novax.ex.common.util;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "通用分页查询对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {
    @Parameter(description = "当前页，默认1", example = "1")
    private Integer page;

    @Parameter(description = "当前页大小，默认10", example = "10")
    private Integer pageSize;

    @Parameter(description = "排序字段，默认id", example = "id")
    private String sort;

    @Parameter(description = "排列顺序，默认desc", example = "desc")
    private String order;
}
