package com.novax.ex.demo.seata.business.service;

import com.novax.ex.demo.seata.business.api.OrderApi;
import com.novax.ex.demo.seata.business.api.StockApi;
import com.novax.ex.demo.seata.business.infrastructure.entity.Order;
import com.novax.ex.demo.seata.business.infrastructure.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/03
 */
@Service
public class BusinessService {
    @Resource
    private OrderApi orderApi;
    @Resource
    private StockApi stockApi;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 下单：创建订单
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @GlobalTransactional
    public void commit(String id, String userId, String commodityCode, Integer count) {
        orderApi.placeOrderCommit(id);
        stockApi.deduct(commodityCode, count);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    public void rollback(String id, String userId, String commodityCode, Integer count) {
        orderApi.placeOrderRollback(id);
        stockApi.deduct(commodityCode, count);
    }

    @GlobalLock(lockRetryInternal = 100, lockRetryTimes = 100)
    public void updateOrder(){
        Order order = new Order().setId(14);
        order.setCount(3);
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
