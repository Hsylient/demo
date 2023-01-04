package com.novax.ex.demo.provider.service;

import com.novax.ex.common.base.BaseService;
import com.novax.ex.common.core.redis.RedisDistributedLocker;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo.infrastructure.entity.DemoEntity;
import com.novax.ex.demo.infrastructure.mapper.DemoMapper;
import com.novax.ex.demo.open.model.response.DemoReponse;
import com.novax.ex.demo.provider.api.Demo1Api;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Description: UserServiceImpl
 *
 * @author shaw
 * @date 6/24/22 14:59
 */
@Service
@Slf4j
public class DemoService extends BaseService<DemoMapper, DemoEntity> {
    @Resource
    private Demo1Api demo1Api;
    @Resource
    private RedisDistributedLocker redisDistributedLocker;

    public DemoReponse getDemo(Long id) {
        return CopyUtils.copyObject(mapper.selectByPrimaryKey(id), DemoReponse.class);
    }

    public void deleteDemo(Long id) {
        mapper.deleteByPrimaryKey(id);
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


    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean modify(DemoEntity entity) {
        Integer age = entity.getAge();
        if (!Boolean.TRUE.equals(demo1Api.incr(entity.getId(), age).getData())) {
            throw new RuntimeException("incr fail");
        }
        int update = mapper.update(entity);
        if (update <= 0) {
            throw new RuntimeException("update fail");
        }
        // 随机抛出异常
        int i = 1 / ThreadLocalRandom.current().nextInt(2);
        return true;
    }
}
