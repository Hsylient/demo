package com.novax.ex.common.util;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Description:分页工具类
 *
 * @author shaoqiping
 * @date 2018年2月24日上午9:21:27
 */
@Data
public class PageUtil<T>{

    /** 默认当前页 */
    private static final Integer DEFAULT_PAGE = 1;
    /** 默认单页条数 */
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    /** 默认排序关键字 */
    private static final String DEFAULT_SORT ="id";
    /** 默认排序顺序-倒序 */
    private static final String DEFAULT_ORDER ="desc";
    /** 记录 */
    @Schema(description = "响应数据")
    private List<T> items;
    /** 总记录数 */
    @Schema(description = "总记录数")
    private Long total;
    /** 当前页数 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private Integer page;
    /** 单页总条数 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private Integer pageSize;
    /** 分页起始 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private Integer rowStart;
    /** 分页结束 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private Integer rowEnd;
    /** 排序关键字 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private String sort;
    /** 排序顺序 asc 顺序 desc 倒序 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private String order;
    /** 排序规则 例如：order by id asc ,name desc */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private String orders;
    /** 显示条目数 例如：limit 0,10*/
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private String limits;
    /** 筛选条件 */
    @JSONField(serialize=false)
    @Schema(hidden = true)
    private Map<String, Object> condition = new HashMap<>();

    public PageUtil() {

    }

    public PageUtil(Integer page, Integer pageSize) {
        if (null == page || page <= 0) {
            this.page = DEFAULT_PAGE;
        } else {
            this.page = page;
        }
        if (null == pageSize || pageSize <= 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
        this.rowStart = (this.page - 1) * this.pageSize;
        this.rowEnd = this.pageSize;
        this.limits = " limit "+rowStart+","+rowEnd;
    }

    public PageUtil(PageQuery query){
        this(query.getPage(), query.getPageSize(), query.getSort(), query.getOrder());
    }

    public PageUtil(Integer page, Integer pageSize, String sort, String order) {
        this(page, pageSize);
        if (!StringUtils.isEmpty(sort)) {
            //防止sql注入
            if (Pattern.matches("[a-zA-Z0-9_]*",sort)){
                this.sort = sort;
            }else {
                this.sort = DEFAULT_SORT;
            }
        } else {
            this.sort = DEFAULT_SORT;
        }
        if (!StringUtils.isEmpty(order)) {
            //防止sql注入
            if ("ASC".equals(order.toUpperCase()) || "DESC".equals(order.toUpperCase())){
                this.order = order;
            }else {
                this.order = DEFAULT_ORDER;
            }
        } else {
            this.order = DEFAULT_ORDER;
        }
        this.orders = "order by " + this.sort + " " + this.order;
    }

    public PageUtil(List<T> items, Long total) {
        super();
        this.items = items;
        this.total = total;
    }
}
