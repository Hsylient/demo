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

@FeignClient(value = "order", contextId = "OrderApi")
public interface OrderApi {

    @Operation(summary = "提交")
    @GetMapping(path = "/v3/private/demo/seata/order/placeOrder/commit")
    ReturnResult placeOrderCommit();

    @Operation(summary = "回滚")
    @GetMapping(path = "/v3/private/demo/seata/order/placeOrder/rollback")
    ReturnResult placeOrderRollback(@RequestParam(value = "id") String id);
}
