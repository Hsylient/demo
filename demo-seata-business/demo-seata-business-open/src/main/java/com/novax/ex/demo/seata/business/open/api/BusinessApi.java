package com.novax.ex.demo.seata.business.open.api;

import com.novax.ex.common.results.ReturnResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author david
 * @date 2023/03/03
 */
@Tag(name = "业务", description = "业务操作")
@RequestMapping
public interface BusinessApi {
    @GetMapping(path = "/v3/public/demo/seata/business/placeOrder/commit")
    ReturnResult placeOrderCommit();

    @GetMapping(path = "/v3/public/demo/seata/business/placeOrder/rollback")
    ReturnResult placeOrderRollback(@RequestParam(value = "id") String id);

    @GetMapping(path = "/v3/public/demo/seata/business/updateOrder")
    ReturnResult updateOrder();
}
