package com.novax.ex.demo.provider.controller;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo.infrastructure.entity.MongoEntity;
import com.novax.ex.demo.open.api.MongoApi;
import com.novax.ex.demo.open.model.query.MongoDemoQuery;
import com.novax.ex.demo.open.model.request.MongoRequest;
import com.novax.ex.demo.open.model.response.MongoRepose;
import com.novax.ex.demo.provider.service.MongoService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author my.miao
 * @date 2022/6/29 18:27
 */
@RestController
public class MongoController implements MongoApi {

    @Resource
    private MongoService mongoService;

    @Override
    public ReturnResult<MongoRepose> getMongo(Long id) {
        MongoEntity mongoEntity = mongoService.byId(id);
        return ReturnResult.success(CopyUtils.copyObject(mongoEntity, MongoRepose.class));
    }

    @Override
    public ReturnResult mongoPost(MongoRequest request) {
        mongoService.mongoPost(CopyUtils.copyObject(request, MongoEntity.class));
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult mongoPut(Long id, MongoRequest request) {
        MongoEntity entity = CopyUtils.copyObject(request, MongoEntity.class);
        entity.setId(id);
        mongoService.updateById(entity);
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult updateColumn(MongoRequest request) {
        MongoEntity entity = CopyUtils.copyObject(request, MongoEntity.class);
        mongoService.updateColumn(entity);
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult mongoDelete(Long id) {
        mongoService.deleteById(id);
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult getMongoQuery(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.query(query));
    }

    @Override
    public ReturnResult getMongoQueryAnd(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.getMongoQueryAnd(query));
    }

    @Override
    public ReturnResult getMongoQueryAndOr(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.getMongoQueryAndOr(query));
    }

    @Override
    public ReturnResult getMongoQueryLike(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.getMongoQueryLike(query));
    }

    @Override
    public ReturnResult page(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.page(query));
    }

    @Override
    public ReturnResult sort(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.sort(query));
    }

    @Override
    public ReturnResult index(String keyName,Integer sort) {
        mongoService.index(keyName,sort);
        return ReturnResult.success();
    }

    @Override
    public ReturnResult sum(MongoDemoQuery query) {
        return ReturnResult.success(mongoService.sum(query));
    }
}
