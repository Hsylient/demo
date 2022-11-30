package com.novax.ex.demo.provider.controller;

import com.novax.ex.demo.open.api.ElasticsearchApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * es demo
 *
 * @author zhenghao
 * @date 2022/8/23 10:33
 */
@RestController
public class ElasticsearchController implements ElasticsearchApi {
//    @Resource
//    private ElasticsearchService elasticsearchService;
//
//    @Override
//    public ReturnResult<List<EsResponse>> list() {
//        return ReturnResult.success(elasticsearchService.list());
//    }
//
//    @Override
//    public ReturnResult<EsResponse> findById(String id) {
//        return ReturnResult.success(EntityToDtoUtil.copyObject(elasticsearchService.find(id), EsResponse.class));
//    }
//
//    @Override
//    public ReturnResult<Boolean> add(EsRequest request) {
//        ArticleEntity entity = EntityToDtoUtil.copyObject(request, ArticleEntity.class);
//        return ReturnResult.success(elasticsearchService.add(entity));
//    }
//
//    @Override
//    public ReturnResult<Boolean> modify(EsRequest request) {
//        ArticleEntity entity = EntityToDtoUtil.copyObject(request, ArticleEntity.class);
//        return ReturnResult.success(elasticsearchService.edit(entity));
//    }
//
//    @Override
//    public ReturnResult<Boolean> remove(String id) {
//        return ReturnResult.success(elasticsearchService.remove(id));
//    }
}
