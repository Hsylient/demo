package com.novax.ex.demo.sp.tcc.order.open;

import com.novax.ex.common.results.ReturnResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author david
 * @date 2023/03/28
 */
@Tag(name = "订单", description = "订单操作")
@RequestMapping
public interface OrderApi {
    @GetMapping(path = "/v3/private/demo/seata/order/placeOrder/commit")
    ReturnResult placeOrderCommit() throws InterruptedException;

    @GetMapping(path = "/v3/private/demo/seata/order/placeOrder/rollback")
    ReturnResult placeOrderRollback(@RequestParam(value = "id") String id) throws InterruptedException;
}
