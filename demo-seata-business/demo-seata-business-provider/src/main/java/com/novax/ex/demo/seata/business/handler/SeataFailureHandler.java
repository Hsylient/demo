package com.novax.ex.demo.seata.business.handler;

import io.seata.tm.api.DefaultFailureHandlerImpl;
import io.seata.tm.api.GlobalTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author david
 * @date 2023/03/07
 */
@Slf4j
@Configuration("failureHandler")
public class SeataFailureHandler extends DefaultFailureHandlerImpl {

    @Override
    public void onBeginFailure(GlobalTransaction tx, Throwable cause) {
        super.onBeginFailure(tx, cause);
    }

    @Override
    public void onCommitFailure(GlobalTransaction tx, Throwable cause) {
        super.onCommitFailure(tx, cause);
    }

    @Override
    public void onRollbackFailure(GlobalTransaction tx, Throwable originalException) {
        super.onRollbackFailure(tx, originalException);
    }

    @Override
    public void onRollbackRetrying(GlobalTransaction tx, Throwable originalException) {
        super.onRollbackRetrying(tx, originalException);
    }
}
