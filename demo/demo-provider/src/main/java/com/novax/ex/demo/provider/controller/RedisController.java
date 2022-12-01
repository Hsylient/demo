package com.novax.ex.demo.provider.controller;

import cn.hutool.core.util.StrUtil;
import com.novax.ex.common.core.redis.RedisUtil;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.api.RedisApi;
import com.novax.ex.demo.open.model.request.RedisStringRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author mars
 * @description RedisController
 * @date 2022-11-30 19:04
 */
@RestController
public class RedisController implements RedisApi {

    @Override
    public ReturnResult<?> stringSet(RedisStringRequest body) {
        String key = body.getKey();
        if (StrUtil.isEmpty(key)) {
            return ReturnResult.fail("缺少key");
        }
        TimeUnit timeUnit = timeUnitConvert(body.getUnit());
        Long time = body.getTime();
        Object value = body.getValue();
        if (time != null && time >= 0L) {
            RedisUtil.set(key, value, time, timeUnit);
            return ReturnResult.success("设置带过期时间key-value成功");
        }
        RedisUtil.set(key, value);
        return ReturnResult.success("设置成功");
    }

    @Override
    public ReturnResult<?> stringGet(String key) {
        if (StrUtil.isEmpty(key)) {
            return ReturnResult.fail("缺少key");
        }
        return ReturnResult.success(RedisUtil.get(key));
    }

    @Override
    public ReturnResult<?> delete(RedisStringRequest body) {
        String key = body.getKey();
        if (StrUtil.isEmpty(key)) {
            return ReturnResult.fail("缺少key");
        }
        RedisUtil.delete(key);
        return ReturnResult.success("删除成功");
    }

    private TimeUnit timeUnitConvert(String unit) {
        if (unit == null) return TimeUnit.SECONDS;
        switch (unit.toUpperCase()) {
            case "NANOSECONDS":
                return TimeUnit.NANOSECONDS;
            case "MICROSECONDS":
                return TimeUnit.MICROSECONDS;
            case "MILLISECONDS":
                return TimeUnit.MILLISECONDS;
            case "SECONDS":
                return TimeUnit.SECONDS;
            case "MINUTES":
                return TimeUnit.MINUTES;
            case "HOURS":
                return TimeUnit.HOURS;
            case "DAYS":
                return TimeUnit.DAYS;
        }
        return TimeUnit.SECONDS;
    }
}
