package com.novax.ex.demo.open.model.response;

import lombok.Data;

import java.util.Date;

/**
 * 文档模型
 */
@Data
public class EsResponse {
    private String id;
    private String title;
    private String content;
    private Long userId;
    private Date createTime;
}

