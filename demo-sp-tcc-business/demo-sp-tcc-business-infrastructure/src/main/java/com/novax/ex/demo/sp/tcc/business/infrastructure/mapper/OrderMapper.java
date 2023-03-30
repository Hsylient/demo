package com.novax.ex.demo.sp.tcc.business.infrastructure.mapper;


import com.novax.ex.demo.sp.tcc.business.infrastructure.entity.Order;

/**
 * @author david
 * @date 2023/03/02
 */

public interface OrderMapper {
    int insert(Order order);

    int updateByPrimaryKeySelective(Order order);
}
