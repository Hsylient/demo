package com.novax.ex.demo.sp.tcc.stock.provider.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author david
 * @date 2023/03/29
 */
@LocalTCC
public interface StockAction {

    @TwoPhaseBusinessAction(name = "prepareDeductStock", commitMethod = "commitDeductStock", rollbackMethod = "rollbackDeductStock")
    boolean prepareDeductStock(BusinessActionContext context,
                               @BusinessActionContextParameter("commodityCode") String commodityCode,
                               @BusinessActionContextParameter("count") int count);

    boolean commitDeductStock(BusinessActionContext context);

    boolean rollbackDeductStock(BusinessActionContext context);
}
