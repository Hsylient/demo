package com.novax.ex.demo.sp.tcc.stock.provider.tcc.impl;

import com.novax.ex.demo.sp.tcc.stock.infrastructure.mapper.StockMapper;
import com.novax.ex.demo.sp.tcc.stock.provider.tcc.StockAction;
import com.novax.ex.demo.sp.tcc.stock.provider.tcc.TccActionResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author david
 * @date 2023/03/29
 */
@Component
public class StockActionImpl implements StockAction {
    @Resource
    private StockMapper stockMapper;

    @Override
    @Transactional
    public boolean prepareDeductStock(BusinessActionContext context, String commodityCode, int count) {
        String xid = context.getXid();
        // 幂等性判断
        if (TccActionResult.hasPrepareResult(xid)) {
            return true;
        }
        // 避免空悬挂，已经执行过回滚了就不能再预留资源
        if (TccActionResult.hasRollbackResult(xid) || TccActionResult.hasCommitResult(xid)) {
            return false;
        }
        // 预留资源
        boolean result = stockMapper.prepareDeductStock(commodityCode, count) > 0;
        // 记录执行结果：xid 以便回滚时判断是否是空回滚
        TccActionResult.prepareSuccess(xid);
        return result;
    }

    @Override
    @Transactional
    public boolean commitDeductStock(BusinessActionContext context) {
        String xid = context.getXid();
        // 幂等性判断
        if (TccActionResult.hasCommitResult(xid)) {
            return true;
        }

        Map<String, Object> actionContext = context.getActionContext();
        String commodityCode = (String) actionContext.get("commodityCode");
        int count = (int) actionContext.get("count");

        // 执行提交操作，扣除预留款
        boolean result = stockMapper.commitDeductStock(commodityCode, count) > 0;


        // 清除预留结果
        TccActionResult.removePrepareResult(xid);
        // 设置提交结果
        TccActionResult.commitSuccess(xid);
        return result;
    }

    @Override
    @Transactional
    public boolean rollbackDeductStock(BusinessActionContext context) {
        String xid = context.getXid();
        // 幂等性判断
        if (TccActionResult.hasRollbackResult(xid)) {
            return true;
        }
        // 没有预留资源结果，回滚不做任何处理；
        if (!TccActionResult.hasPrepareResult(xid)) {
            // 设置回滚结果，防止空悬挂
            TccActionResult.rollbackResult(xid);
            return true;
        }

        // 执行回滚
        Map<String, Object> actionContext = context.getActionContext();
        String commodityCode = (String) actionContext.get("commodityCode");
        int count = (int) actionContext.get("count");
        boolean result = stockMapper.rollbackDeductStock(commodityCode, count) > 0;

        // 清除预留结果
        TccActionResult.removePrepareResult(xid);
        // 设置回滚结果
        TccActionResult.rollbackResult(xid);
        return result;
    }
}
