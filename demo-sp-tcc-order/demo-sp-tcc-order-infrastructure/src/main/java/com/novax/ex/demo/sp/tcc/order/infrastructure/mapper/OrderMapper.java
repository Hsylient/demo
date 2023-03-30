package com.novax.ex.demo.sp.tcc.order.infrastructure.mapper;


import com.novax.ex.demo.sp.tcc.order.infrastructure.entity.Order;

/**
 * @author david
 * @date 2023/03/28
 */

public interface OrderMapper {
    int insert(Order order);

    int updateByPrimaryKeySelective(Order order);

    int deleteByPrimaryKey(Order order);
}
