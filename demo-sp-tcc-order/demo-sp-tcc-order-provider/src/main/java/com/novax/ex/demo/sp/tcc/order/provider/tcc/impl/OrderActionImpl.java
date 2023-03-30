package com.novax.ex.demo.sp.tcc.order.provider.tcc.impl;

import com.novax.ex.demo.sp.tcc.order.infrastructure.entity.Order;
import com.novax.ex.demo.sp.tcc.order.infrastructure.mapper.OrderMapper;
import com.novax.ex.demo.sp.tcc.order.provider.tcc.OrderAction;
import com.novax.ex.demo.sp.tcc.order.provider.tcc.TccActionResult;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author david
 * @date 2023/03/28
 */
@Component
public class OrderActionImpl implements OrderAction {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public boolean prepareOrder(BusinessActionContext context, String userId, String id, String commodityCode, int count, long price) {
        String xid = context.getXid();
        // 幂等性判断
        if (TccActionResult.hasPrepareResult(xid)) {
            return true;
        }

        // 避免空悬挂，已经执行过回滚了就不能再预留资源
        if (TccActionResult.hasRollbackResult(xid) || TccActionResult.hasCommitResult(xid)) {
            return false;
        }

        // 预下单
        int result = 0;
        if(StringUtils.isBlank(id)){
            BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
            Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(orderMoney);
            order.setStatus("PREPARE");
            result = orderMapper.insert(order);
            BusinessActionContextUtil.addContext("orderId", order.getId());
        } else {
            Integer orderId = Integer.valueOf(id);
            Order order = new Order().setId(orderId).setCount(2);
            order.setStatus("PREPARE");
            result = orderMapper.updateByPrimaryKeySelective(order);
            BusinessActionContextUtil.addContext("orderId", orderId);
        }

        // 记录主键ID，为了传递给提交或回滚
        BusinessActionContextUtil.addContext("id", id);
        TccActionResult.prepareSuccess(xid);
        return result > 0;
    }

    @Override
    @Transactional
    public boolean commitOrder(BusinessActionContext context) {
        String xid = context.getXid();
        // 幂等性判断
        if (TccActionResult.hasCommitResult(xid)) {
            return true;
        }

        // 修改数据或状态
        int result = 0;
        String id = (String) context.getActionContext("id");
        Integer orderId = (Integer) context.getActionContext("orderId");
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("COMMIT");
        result = orderMapper.updateByPrimaryKeySelective(order);

        // 清除预留结果
        TccActionResult.removePrepareResult(xid);
        // 设置提交结果
        TccActionResult.commitSuccess(xid);
        return result > 0;
    }

    @Override
    @Transactional
    public boolean rollbackOrder(BusinessActionContext context) {
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

        // 删除订单或更新数据
        int result = 0;
        String id = (String) context.getActionContext("id");
        Integer orderId = (Integer) context.getActionContext("orderId");
        Order order = new Order();
        order.setId(orderId);
        if(StringUtils.isBlank(id)){
            result = orderMapper.deleteByPrimaryKey(order);
        } else {
            order.setCount(1);
            order.setStatus("ROLLBACK");
            result = orderMapper.updateByPrimaryKeySelective(order);
        }

        // 清除预留结果
        TccActionResult.removePrepareResult(xid);
        // 设置回滚结果
        TccActionResult.rollbackResult(xid);
        return result > 0;
    }
}
