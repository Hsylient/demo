package com.novax.ex.demo.provider;

import com.novax.ex.common.core.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author mars
 * @description redis工具类测试
 * @date 2022-11-30 17:38
 */
@SpringBootTest
public class RedisUtilsTest {

    @Test
    public void testSet() {
        RedisUtil.set("key1", "value1", 5, TimeUnit.MINUTES);
        Long expire = RedisUtil.getExpire("key1");
        System.out.println("expire = " + expire);
    }

}
