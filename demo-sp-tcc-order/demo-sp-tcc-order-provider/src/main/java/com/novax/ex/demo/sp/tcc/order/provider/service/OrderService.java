package com.novax.ex.demo.sp.tcc.order.provider.service;

import com.novax.ex.demo.sp.tcc.order.provider.tcc.OrderAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/28
 */
@Service
public class OrderService{
    @Resource
    private OrderAction orderAction;

    /**
     * 下单：创建订单
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @Transactional
    public Boolean placeOrder(String userId, String id, String commodityCode, Integer count) {
        return orderAction.prepareOrder(null, userId, id, commodityCode, count, 5);
    }
}
