package com.novax.ex.demo.sp.tcc.business.provider.service;

import com.novax.ex.common.results.CurrencyMessage;
import com.novax.ex.demo.sp.tcc.business.infrastructure.entity.Order;
import com.novax.ex.demo.sp.tcc.business.infrastructure.mapper.OrderMapper;
import com.novax.ex.demo.sp.tcc.business.provider.api.OrderApi;
import com.novax.ex.demo.sp.tcc.business.provider.api.StockApi;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/28
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
    public boolean commit(String id, String userId, String commodityCode, Integer count){
        // 先扣款，扣款成功就创建订单，扣减库存在创建订单的逻辑里面
        if (CurrencyMessage.SUCCESS.getCode() == orderApi.placeOrderCommit().getCode()
                && CurrencyMessage.SUCCESS.getCode() == stockApi.deduct(commodityCode, count).getCode()) {
            return true;
        }
        // 下单失败
        return false;
    }

    @GlobalTransactional
    public boolean rollback(String id, String userId, String commodityCode, Integer count) {
        if(CurrencyMessage.SUCCESS.getCode() == orderApi.placeOrderRollback(id).getCode()
                && CurrencyMessage.SUCCESS.getCode() == stockApi.deduct(commodityCode, count).getCode()){
            return true;
        }
        // 下单失败
        return false;
    }

    @Transactional
    public void updateOrder(){
        Order order = new Order().setId(1);
        order.setCount(3);
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
