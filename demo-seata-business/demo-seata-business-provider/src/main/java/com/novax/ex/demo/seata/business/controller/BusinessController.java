package com.novax.ex.demo.seata.business.controller;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.seata.business.open.api.BusinessApi;
import com.novax.ex.demo.seata.business.service.BusinessService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author david
 * @date 2023/03/03
 */
@RestController
public class BusinessController implements BusinessApi {
    @Resource
    private BusinessService businessService;

    /**
     * 下单：提交
     *
     * @return
     */
    public ReturnResult placeOrderCommit() {
        businessService.commit("1", "product-1", 1);
        return ReturnResult.success();
    }

    /**
     * 下单：模拟回滚
     *
     * @return
     */
    public ReturnResult placeOrderRollback() {
        // product-2 扣库存时模拟了一个业务异常
        businessService.rollback("1", "product-2", 1);
        return ReturnResult.success();
    }
}
