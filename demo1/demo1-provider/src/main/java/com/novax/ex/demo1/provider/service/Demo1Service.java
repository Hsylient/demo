package com.novax.ex.demo1.provider.service;

import com.novax.ex.common.core.redis.RedisDistributedLocker;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo1.infrastructure.entity.DemoEntity;
import com.novax.ex.demo1.infrastructure.mapper.Demo1Mapper;
import com.novax.ex.demo1.open.model.response.DemoReponse;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Description: UserServiceImpl
 *
 * @author shaw
 * @date 6/24/22 14:59
 */
@Service
@Slf4j
public class Demo1Service {

    @Resource
    private Demo1Mapper demo1Mapper;
    @Resource
    private RedisDistributedLocker redisDistributedLocker;

    public DemoReponse getDemo(Long id) {
        return CopyUtils.copyObject(demo1Mapper.selectByPrimaryKey(id), DemoReponse.class);
    }

    public void deleteDemo(Long id) {
        demo1Mapper.deleteByPrimaryKey(id);
    }

    public void redisLockDemo() {
        boolean temp = redisDistributedLocker.tryLock("testlock", false);
        log.info("" + temp);
        if (temp) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                redisDistributedLocker.unlock("testlock");
            }
        }
    }

    public void redisLockDemo2() {
        RLock rLock = redisDistributedLocker.lock("testlock", false);
        log.info("" + rLock.isLocked());
        if (rLock.isLocked()) {
            try {
                System.out.println(1234);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                redisDistributedLocker.unlock(rLock);
            }
        }
    }


    public Boolean modify(DemoEntity entity) {
        return demo1Mapper.update(entity) > 0;
    }

    public Boolean add(DemoEntity entity) {
        return demo1Mapper.insert(entity) > 0;
    }

    public Boolean incr(Long id, Integer num) {
        return demo1Mapper.incr(id, num) > 0;
    }
}
