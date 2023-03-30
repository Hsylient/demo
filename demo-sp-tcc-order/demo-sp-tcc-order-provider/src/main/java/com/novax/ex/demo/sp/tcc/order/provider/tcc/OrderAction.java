package com.novax.ex.demo.sp.tcc.order.provider.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author david
 * @date 2023/03/28
 */
@LocalTCC
public interface OrderAction {
    @TwoPhaseBusinessAction(name = "prepareOrder", commitMethod = "commitOrder", rollbackMethod = "rollbackOrder")
    boolean prepareOrder(BusinessActionContext context,

                         @BusinessActionContextParameter(paramName = "userId") String userId,
                         String id,
                         String commodityCode,
                         int count,
                         long price);

    boolean commitOrder(BusinessActionContext context);

    boolean rollbackOrder(BusinessActionContext context);
}
