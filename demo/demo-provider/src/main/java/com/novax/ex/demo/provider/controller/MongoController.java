package com.novax.ex.demo.provider.controller;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo.infrastructure.entity.MongoEntity;
import com.novax.ex.demo.open.api.MongoApi;
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
    public ReturnResult<List<MongoRepose>> getMongos() {
        List<MongoRepose> list = CopyUtils.copyList(mongoService.getMongos(),MongoRepose.class);
        return new ReturnResult<>(200, "成功", list);
    }

    @Override
    public ReturnResult<MongoRepose> getMongo(Long id) {
        return null;
    }

    @Override
    public ReturnResult<List<MongoRepose>> getMongoBySymbol(String symbol) {
        List<MongoRepose> list = CopyUtils.copyList(mongoService.getMongoBySymbol(symbol),MongoRepose.class);
        return new ReturnResult<>(200, "成功", list);
    }

    @Override
    public ReturnResult mongoPost(MongoRequest request) {
        mongoService.mongoPost(CopyUtils.copyObject(request, MongoEntity.class));
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult mongoPut(Long id, MongoRequest request) {
        return null;
    }

    @Override
    public ReturnResult mongoDelete(Long id) {
        return null;
    }

    @Override
    public ReturnResult<List<MongoRepose>> page() {
        return null;
    }
}
