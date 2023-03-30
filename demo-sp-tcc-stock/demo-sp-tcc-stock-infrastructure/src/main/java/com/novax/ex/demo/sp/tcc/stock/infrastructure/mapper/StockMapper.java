package com.novax.ex.demo.sp.tcc.stock.infrastructure.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author david
 * @date 2023/03/28
 */
public interface StockMapper {
    int prepareDeductStock(@Param("commodityCode") String commodityCode, @Param("count") int count);
    int commitDeductStock(@Param("commodityCode") String commodityCode, @Param("count") int count);
    int rollbackDeductStock(@Param("commodityCode") String commodityCode, @Param("count") int count);
}
