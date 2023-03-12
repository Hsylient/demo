package com.novax.ex.demo.seata.stock.provider.service;

import com.novax.ex.demo.seata.stock.infrastructure.entity.Stock;
import com.novax.ex.demo.seata.stock.infrastructure.mapper.StockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author david
 * @date 2023/03/03
 */
@Service
public class StockService{
    @Resource
    private StockMapper stockMapper;

    /**
     * 减库存
     * @param commodityCode 商品码
     * @param count 数量
     */
    @Transactional()
    public void deduct(String commodityCode, int count) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }

        Stock stock = stockMapper.findByCommodityCode(commodityCode);
        stock.setCount(stock.getCount() - count);
        stockMapper.updateById(stock);
    }
}
