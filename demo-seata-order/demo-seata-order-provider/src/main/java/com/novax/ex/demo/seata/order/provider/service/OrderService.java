package com.novax.ex.demo.seata.order.provider.service;

import com.novax.ex.demo.seata.order.infrastructure.entity.Order;
import com.novax.ex.demo.seata.order.infrastructure.mapper.OrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    public void placeOrder(String id, String userId, String commodityCode, Integer count) throws InterruptedException {
        if(StringUtils.isBlank(id)){
            BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
            Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(orderMoney);
            orderMapper.insert(order);
            return;
        }
        Order order = new Order().setId(Integer.valueOf(id)).setCount(2);
        orderMapper.updateByPrimaryKeySelective(order);}
}
