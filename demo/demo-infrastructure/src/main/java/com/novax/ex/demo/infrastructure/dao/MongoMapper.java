package com.novax.ex.demo.infrastructure.dao;

import com.novax.ex.demo.infrastructure.entity.MongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author my.miao
 * @date 2022/6/29 18:35
 */
@Repository
public interface MongoMapper extends MongoRepository<MongoEntity, Long> {
    /**
     * 最常用的就是下面三个
     * value={} 条件
     * filed={} 返回指定字段，1 代表返回 0 带表隐藏
     * sort={}  排序 sort = "{age:-1}")  -1 表示 desc 1 表示 asc
     **/
    //?0 代表参数的第一个位置 ?1 带表参数的第二个位置以此类推
    @Query(value = "{name: ?0}", fields = "{_id:0}")
    List<MongoEntity> querySimple(String name, Integer age);

    //@Query (value = "{'$and' : [
    // { 'id' : ?0 },
    // { 'time' : { '$gte' : ?1 } },
    // { 'age' : { '$lte' : '2' } }
    // ] } ")
    // 语法解释 当使用 关键字时两边需要{}包裹起来，因为语法需要他是一个可识别的json,当碰到key为关键字的时候由其自己组装，多个集合的话就用[]去包
    @Query(value = "{'$and':[{name: ?0},{age: ?1}]}")
    List<MongoEntity> getMongoQueryAnd(String name, Integer age);

    // and 和 or 同时使用
    // @Query (value = " { '$and' : [
    //                                 {'$or' :
    //                                    [{'name': ?#{{$regex : [0]}}},
    //                                     {'address': ?#{{$regex : [0]}}}
    //                                     ]},
    //                                 {'time':{ '$gte' : ?1 }},
    //                                 {'age' : '3 '}
    //                               ]}")
    // 只要掌握了其中的一种之后，复杂的就是组合，拼接规则还是按照单个的那种用{},[]进行包裹，关键字当key。
    @Query(value = "{'$and':['$or':{'name': ?0},{'age':?1}]}")
    List<MongoEntity> getMongoQueryAndOr(String name, Integer age);

    //$regex 此关键字是like
    @Query (value = "{'name':?#{{$regex:[0]}}}")
    List<MongoEntity> getMongoQueryLike(String name);

    @Query (value = "{'name':?#{{$regex:[0]}}}")
    Page<MongoEntity> page(String name, Pageable pageable);

    //-1 表示 desc 1 表示 asc
    @Query (value = "{'name':?#{{$regex:[0]}}}",sort = "{sort:-1}")
    List<MongoEntity> sort(String name);

}
