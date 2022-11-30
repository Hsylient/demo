package com.novax.ex.demo1.open.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Description: UserReponse
 *
 * @author shaw
 * @date 6/24/22 14:54
 */
@Data
@Schema(description = "DemoReponse")
public class DemoReponse {
    private Long id;

    private String name;

    private Integer age;

    private String symbol;
}
