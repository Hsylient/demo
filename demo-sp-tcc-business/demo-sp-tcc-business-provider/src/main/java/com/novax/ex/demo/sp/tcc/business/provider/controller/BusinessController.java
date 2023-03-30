package com.novax.ex.demo.sp.tcc.business.provider.controller;

import com.novax.ex.common.constant.StatusCode;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.sp.tcc.business.open.BusinessApi;
import com.novax.ex.demo.sp.tcc.business.provider.service.BusinessService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author david
 * @date 2023/03/28
 */
@RestController
public class BusinessController implements BusinessApi {
    private static ExecutorService executorService = new ThreadPoolExecutor(
            1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>()
    );
    @Resource
    private BusinessService businessService;

    /**
     * 下单：提交
     *
     * @return
     */
    public ReturnResult placeOrderCommit() {
        boolean isOk = businessService.commit(null, "1", "product-1", 1);
        if(isOk){
            return ReturnResult.success();
        }
        return ReturnResult.fail(StatusCode.ERROR.toString());
    }

    /**
     * 下单：模拟回滚
     *
     * @return
     */
    public ReturnResult placeOrderRollback(String id) {
        // product-2 扣库存时模拟了一个业务异常
        boolean isOk = businessService.rollback(id, "1", "product-2", 1);
        if(isOk){
            return ReturnResult.success();
        }
        return ReturnResult.fail(StatusCode.ERROR.toString());
    }

    @Override
    public ReturnResult updateOrder() {
        executorService.execute(()-> run());
        return ReturnResult.success();
    }

    private void run(){
        businessService.updateOrder();
    }
}
