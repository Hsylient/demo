package com.novax.ex.demo.seata.stock.open.api;

import com.novax.ex.common.results.ReturnResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author david
 * @date 2023/03/03
 */
@Tag(name = "库存", description = "库存操作")
@RequestMapping
public interface StockApi {
    @GetMapping(path = "/v3/private/demo/seata/stock/deduct")
    ReturnResult deduct(String commodityCode, Integer count);
}
