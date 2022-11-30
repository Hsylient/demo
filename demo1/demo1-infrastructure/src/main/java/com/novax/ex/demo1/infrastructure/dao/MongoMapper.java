package com.novax.ex.demo1.infrastructure.dao;

import com.novax.ex.demo1.infrastructure.entity.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author my.miao
 * @date 2022/6/29 18:35
 */
@Repository
public interface MongoMapper extends MongoRepository<MongoEntity, String> {

    List<MongoEntity> findBySymbol(String symbol);
}
