package com.novax.ex.demo.provider.service;

import com.novax.ex.common.util.Snowflake;
import com.novax.ex.demo.infrastructure.dao.MongoMapper;
import com.novax.ex.demo.infrastructure.dao.MongoResult;
import com.novax.ex.demo.infrastructure.entity.MongoEntity;
import com.novax.ex.demo.open.model.query.MongoDemoQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author my.miao
 * @date 2022/6/29 18:38
 */
@Service
public class MongoService {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private MongoMapper mongoMapper;

    public void mongoPost(MongoEntity entity) {
        //直接使用save方法，会自动判断是否为新增还是修改，进入源码会看到SimpleMongoRepository中有一个isNew方法具体可以进一步查看
        //save:当主键"_id"在集合中存在时，进行更新。 数据整体都会更新 ，新数据会替换掉原数据 ID 以外的所有数据。如ID 不存在就新增一条数据
        //save 方法 需要遍历列表，一个个插入， 而 insert 方法 是直接批量插入
        if (Objects.isNull(entity.getId())) {
            entity.setId(Snowflake.generateId());
            //如果是新增直接调用 MongoTemplate 的insert 方法即可
            mongoTemplate.insert(entity);
        } else {
            mongoMapper.save(entity);
        }
    }

    public MongoEntity byId(Long id) {
        return mongoMapper.findById(id).get();
    }

    public void updateById(MongoEntity entity) {
        //此处修改是一个按照ID覆盖的过程，如果想修改某一个字段此方法不可用，因为他是覆盖的概念，如果是空字段则会被去掉
        mongoMapper.save(entity);
    }

    public void updateColumn(MongoEntity entity) {
        // 此方法可以实现指定字段修改值
        Query query = new Query(Criteria.where("_id").is(entity.getId()));
        Update update = new Update();
        //需要一个一个去判断赋值
        if (Objects.nonNull(entity.getAge())) {
            update.set("age", entity.getAge());
        }
        if (Objects.nonNull(entity.getName())) {
            update.set("name", entity.getName());
        }
        mongoTemplate.updateFirst(query, update, MongoEntity.class);
    }

    public void deleteById(Long id) {
        mongoMapper.deleteById(id);
    }

    public List<MongoEntity> query(MongoDemoQuery query) {
        return mongoMapper.querySimple(query.getName(), query.getAge());
    }

    public List<MongoEntity> getMongoQueryAnd(MongoDemoQuery query) {
        return mongoMapper.getMongoQueryAnd(query.getName(), query.getAge());
    }

    public List<MongoEntity> getMongoQueryAndOr(MongoDemoQuery query) {
        return mongoMapper.getMongoQueryAndOr(query.getName(), query.getAge());
    }

    public List<MongoEntity> getMongoQueryLike(MongoDemoQuery query) {
        return mongoMapper.getMongoQueryLike(query.getName());
    }

    public Page<MongoEntity> page(MongoDemoQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        return mongoMapper.page(query.getName(), pageable);
    }

    public List<MongoEntity> sort(MongoDemoQuery query) {
        return mongoMapper.sort(query.getName());
    }

    /**
     * @description: 索引可以使用客户端链接手动创建，也可以使用MongoTemplate进行动态创建
     * @author: Wade
     * @date: 12/2/22 6:13 PM
     * @param: [keyName, sort]
     * @return: void
     **/
    public void index(String keyName, Integer sort) {
        Index index = new Index();
        if (sort == 1) {
            index.on(keyName, Sort.Direction.ASC);
        }
        if (sort == -1) {
            index.on(keyName, Sort.Direction.DESC);
        }
        /**
         聚合索引
         Index index = new Index();
         index.on(index_key, Sort.Direction.ASC).on(index_key2, Sort.Direction.ASC);
         mongoTemplate.indexOps(collectionName).ensureIndex(index);
         **/
        mongoTemplate.indexOps("demo").ensureIndex(index);
    }

    public List<MongoResult> sum(MongoDemoQuery query) {
        //聚合就稍微复杂点，需要使用模版进行计算

        //完全匹配
        //Pattern pattern = Pattern.compile("^"+query.getName()+"$", Pattern.CASE_INSENSITIVE);
        //右匹配
        Pattern pattern = Pattern.compile("^.*" + query.getName() + "$", Pattern.CASE_INSENSITIVE);
        //左匹配
        //Pattern pattern = Pattern.compile("^"+query.getName()+".*$", Pattern.CASE_INSENSITIVE);
        //模糊匹配
        //Pattern pattern = Pattern.compile("^.*"+query.getName()+".*$", Pattern.CASE_INSENSITIVE);

        Criteria criteria = Criteria.where("name").regex(pattern);
        //Integer pageSize = 10;
        //Integer startRows = (query.getPage() - 1) * pageSize;
        //if(Objects.nonNull(query.getAge())){
        //    criteria.and("age").lt(query.getAge());
        //}
        Aggregation customerAgg = Aggregation.newAggregation(
                Aggregation.project("name", "age", "money","_id"),//project 指定输出文档和查询条件中使用的字段都需要包涵.
                Aggregation.match(criteria),//match 选择要处理的文档，与fine()类似。
                //group 根据key来分组文档
                Aggregation.group("name")
                        //first 返回每个group的第一条数据，顺序由$sort决定
                        .first("name").as("name")
                        //.first("age").as("age")
                        //.first("money").as("money")
                        //sum 对每个group指定字段值进行累加计算。
                        .sum("age").as("age")
                        .sum("money").as("money")
                        //总条目
                        .count().as("count")
                        //分组后的id集合收集
                        .addToSet("_id").as("ids"),
                //按照指定方式进行聚合后排序
                Aggregation.sort(Sort.Direction.DESC,"age")
                //需要跳过的行数
                //Aggregation.skip(startRows),
                //显示条目数
                //Aggregation.limit(pageSize)

        );
        List<MongoResult> customerList = findSumList(customerAgg, "demo", MongoResult.class);
        return customerList;
    }

    public <T> List<T> findSumList(Aggregation aggregation, String collectionName, Class<T> clazz) {
        AggregationResults<T> aggregate = this.mongoTemplate.aggregate(aggregation, collectionName, clazz);
        List<T> details = aggregate.getMappedResults();
        return details;
    }
}
