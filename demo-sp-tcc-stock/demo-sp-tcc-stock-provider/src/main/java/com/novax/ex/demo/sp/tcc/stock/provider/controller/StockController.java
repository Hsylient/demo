package com.novax.ex.demo.sp.tcc.stock.provider.controller;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.sp.tcc.stock.open.StockApi;
import com.novax.ex.demo.sp.tcc.stock.provider.service.StockService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/28
 */
@RestController
public class StockController implements StockApi {
    @Resource
    private StockService stockService;

    @Override
    public ReturnResult deduct(String commodityCode, Integer count) throws InterruptedException {
        stockService.deduct(commodityCode, count);
        return ReturnResult.success();
    }
}
