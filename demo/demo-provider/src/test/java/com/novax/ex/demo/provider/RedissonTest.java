package com.novax.ex.demo.provider;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * RedissionTest
 *
 * @author zhenghao
 * @date 2022/8/10 13:56
 */
@SpringBootTest
public class RedissonTest {

//    @Resource
//    private RedissonClient redissonClient;

    @Test
    public void test() {
//        System.out.println("redissonClient = " + redissonClient);
//        String id = redissonClient.getId();
//        System.out.println("id = " + id);
        System.out.println("Hello test");
    }

}
