package com.novax.ex.demo.seata.order.provider.controller;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.seata.order.open.OrderApi;
import com.novax.ex.demo.seata.order.provider.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/03
 */
@RestController
public class OrderController implements OrderApi {
    @Resource
    private OrderService orderService;

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     *
     * @return
     */
    @Override
    public ReturnResult placeOrderCommit() throws InterruptedException {
        orderService.placeOrder("1", "product-1", 1);
        return ReturnResult.success();
    }

    /**
     * 下单：插入订单表、扣减库存，模拟回滚
     *
     * @return
     */
    @Override
    public ReturnResult placeOrderRollback() throws InterruptedException {
        // product-2 扣库存时模拟了一个业务异常
        orderService.placeOrder("1", "product-2", 1);

        return ReturnResult.success();
    }
}
