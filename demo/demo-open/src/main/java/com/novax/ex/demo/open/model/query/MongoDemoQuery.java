package com.novax.ex.demo.open.model.query;

import com.novax.ex.common.util.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description mongoDB查询分页对象
 * @Author Wade
 * @Date 12/1/22 5:03 PM
 * @Version 1.0
 */
@Data
public class MongoDemoQuery extends PageQuery {
    @Schema(description = "名字")
    private String name;
    @Schema(description = "名字")
    private Integer age;
}
