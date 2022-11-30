package com.novax.ex.common.base;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Description: 基础类
 *
 * @author shaw
 * @date 6/15/21 13:27
 */
@Data
public class BaseEntity {
    /** id */
    private Long id;

    /** 创建人id */
    private Long creator;

    /** 更新人id */
    private Long updater;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
