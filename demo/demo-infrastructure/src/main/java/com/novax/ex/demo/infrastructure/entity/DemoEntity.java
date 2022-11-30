package com.novax.ex.demo.infrastructure.entity;

import com.novax.ex.common.base.BaseEntity;
import lombok.Data;

/**
 * Description:
 *
 * @author shaw
 * @date 6/24/22 14:51
 */
@Data
public class DemoEntity extends BaseEntity {
    private Long id;

    private String name;

    private Integer age;

    private String symbol;
}
