package com.novax.ex.demo.seata.order.open;

import com.novax.ex.common.results.ReturnResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author david
 * @date 2023/03/03
 */
@Tag(name = "订单", description = "订单操作")
@RequestMapping
public interface OrderApi {
    @GetMapping(path = "/v3/private/demo/seata/order/placeOrder/commit")
    ReturnResult placeOrderCommit() throws InterruptedException;

    @GetMapping(path = "/v3/private/demo/seata/order/placeOrder/rollback")
    ReturnResult placeOrderRollback() throws InterruptedException;
}
