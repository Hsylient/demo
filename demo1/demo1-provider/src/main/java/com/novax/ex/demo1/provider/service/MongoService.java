package com.novax.ex.demo1.provider.service;

import com.novax.ex.common.core.redis.RedisUtil;
import com.novax.ex.demo1.infrastructure.entity.MongoEntity;
import com.novax.ex.demo1.infrastructure.dao.MongoMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author my.miao
 * @date 2022/6/29 18:38
 */
@Service
public class MongoService{

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private MongoMapper mongoMapper;
    @Resource
    private RedisUtil redisUtil;

    public List<MongoEntity> getMongos() {
        return mongoMapper.findAll();
    }

    public void mongoPost(MongoEntity entity) {
        mongoMapper.save(entity);
        mongoTemplate.insert(entity,"market");
        redisUtil.set(entity.getId().toString(), entity);
    }

    public List<MongoEntity> getMongoBySymbol(String symbol) {
        return mongoMapper.findBySymbol(symbol);
    }
}
