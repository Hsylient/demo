package com.novax.ex.demo.sp.tcc.business.provider.api;


import com.novax.ex.common.results.ReturnResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author david
 * @date 2023/03/28
 */

@FeignClient(value = "stock", contextId = "StockApi")
public interface StockApi {

    @Operation(summary = "减库存")
    @GetMapping(value = "/v3/private/demo/seata/stock/deduct")
    ReturnResult deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
