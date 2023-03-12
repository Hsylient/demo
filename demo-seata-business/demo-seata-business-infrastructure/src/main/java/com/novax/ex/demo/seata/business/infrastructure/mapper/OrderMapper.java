package com.novax.ex.demo.seata.business.infrastructure.mapper;


import com.novax.ex.demo.seata.business.infrastructure.entity.Order;

/**
 * @author david
 * @date 2023/03/02
 */

public interface OrderMapper {
    int insert(Order order);

    Order selectByPrimaryKey(Order order);

    Order selectByPrimaryKeyForUpdate(Order order);

    int updateByPrimaryKeySelective(Order order);
}
