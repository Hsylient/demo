package com.novax.ex.demo.sp.tcc.stock.provider.service;

import com.novax.ex.demo.sp.tcc.stock.provider.tcc.StockAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author david
 * @date 2023/03/28
 */
@Service
public class StockService{
    @Resource
    private StockAction stockAction;

    /**
     * 减库存
     * @param commodityCode 商品码
     * @param count 数量
     */
    @Transactional()
    public void deduct(String commodityCode, int count) throws InterruptedException {
        if (commodityCode.equals("product-2")) {
            TimeUnit.SECONDS.sleep(5);
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }

        stockAction.prepareDeductStock(null, commodityCode, count);
    }
}
