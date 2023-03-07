package com.novax.ex.demo.seata.stock.infrastructure.mapper;

import com.novax.ex.demo.seata.stock.infrastructure.entity.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author david
 * @date 2023/03/03
 */
public interface StockMapper {

    Stock selectById(@Param("id") Integer id);

    Stock findByCommodityCode(@Param("commodityCode") String commodityCode);

    int updateById(Stock record);

    void insert(Stock record);

    void insertBatch(List<Stock> records);

    int updateBatch(@Param("list") List<Long> ids, @Param("commodityCode") String commodityCode);
}
