package com.novax.ex.demo.seata.business.service;

import com.novax.ex.demo.seata.business.api.OrderApi;
import com.novax.ex.demo.seata.business.api.StockApi;
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

    /**
     * 下单：创建订单
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @GlobalTransactional
    public void commit(String userId, String commodityCode, Integer count) {
        orderApi.placeOrderCommit();
        stockApi.deduct(commodityCode, count);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    public void rollback(String userId, String commodityCode, Integer count) {
        orderApi.placeOrderCommit();
        stockApi.deduct(commodityCode, count);
    }
}
