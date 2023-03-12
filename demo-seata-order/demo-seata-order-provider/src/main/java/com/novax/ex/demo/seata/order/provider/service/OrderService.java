package com.novax.ex.demo.seata.order.provider.service;

import com.novax.ex.demo.seata.order.infrastructure.entity.Order;
import com.novax.ex.demo.seata.order.infrastructure.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/03
 */
@Service
public class OrderService{
    @Resource
    private OrderMapper orderMapper;

    /**
     * 下单：创建订单
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @Transactional
    public void placeOrder(String userId, String commodityCode, Integer count) throws InterruptedException {
//        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
//        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(
//                orderMoney);
//        orderMapper.insert(order);

        Order order = new Order().setId(14).setCount(2);
        orderMapper.updateByPrimaryKeySelective(order);}
}
