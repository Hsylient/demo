package com.novax.ex.demo.provider.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.novax.ex.common.core.redis.RedisUtil;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.api.RedisApi;
import com.novax.ex.demo.open.model.request.RedisHashRequest;
import com.novax.ex.demo.open.model.request.RedisListRequest;
import com.novax.ex.demo.open.model.request.RedisStringRequest;
import com.novax.ex.demo.open.model.request.RedisZSetRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public ReturnResult<?> listAdd(RedisListRequest body) {
        if (StrUtil.isEmpty(body.getKey())) {
            return ReturnResult.fail("缺少key");
        }
        Long size = RedisUtil.lLeftPushAll(body.getKey(), body.getValues());
        return ReturnResult.success(size);
    }

    @Override
    public ReturnResult<List<Object>> listGet(String key) {
        if (StrUtil.isEmpty(key)) {
            return ReturnResult.fail("缺少key");
        }
        List<Object> list = RedisUtil.lRange(key, 0, -1);
        return ReturnResult.success(list);
    }

    @Override
    public ReturnResult<?> setAdd(RedisListRequest body) {
        if (StrUtil.isEmpty(body.getKey())) {
            return ReturnResult.fail("缺少key");
        }
        Long size = RedisUtil.sAdd(body.getKey(), body.getValues().toArray());
        return ReturnResult.success(size);
    }

    @Override
    public ReturnResult<Set<Object>> setGet(String key) {
        if (StrUtil.isEmpty(key)) {
            return ReturnResult.fail("缺少key");
        }
        Set<Object> set = RedisUtil.setMembers(key);
        return ReturnResult.success(set);
    }

    @Override
    public ReturnResult<?> zSetAdd(RedisZSetRequest body) {
        if (StrUtil.isEmpty(body.getKey())) {
            return ReturnResult.fail("缺少key");
        }
        Boolean isAdd = RedisUtil.zAdd(body.getKey(), body.getValue(), body.getScore());
        return ReturnResult.success(isAdd);
    }

    @Override
    public ReturnResult<Set<Object>> zSetGet(String key) {
        if (StrUtil.isEmpty(key)) {
            return ReturnResult.fail("缺少key");
        }
        Set<Object> set = RedisUtil.zRange(key, 0, -1);
        return ReturnResult.success(set);
    }

    public ReturnResult<?> hashPut(RedisHashRequest body) {
        RedisUtil.hPutAll(body.getKey(), body.getValueMap());
        return ReturnResult.success("put hash success");
    }

    @Override
    public ReturnResult<Map<Object, Object>> hashGet(String key) {
        Map<Object, Object> map = RedisUtil.hGetAll(key);
        return ReturnResult.success(map);
    }

    @Override
    public ReturnResult<?> delete(Set<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return ReturnResult.fail("缺少key");
        }
        Long size = RedisUtil.delete(keys);
        return ReturnResult.success("删除成功, 成功条数：" + size);
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
