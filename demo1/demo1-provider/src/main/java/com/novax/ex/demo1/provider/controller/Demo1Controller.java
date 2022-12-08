package com.novax.ex.demo1.provider.controller;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo1.infrastructure.entity.DemoEntity;
import com.novax.ex.demo1.provider.service.Demo1Service;
import com.novax.ex.demo1.open.api.Demo1Api;
import com.novax.ex.demo1.open.model.request.DemoRequest;
import com.novax.ex.demo1.open.model.response.DemoReponse;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: UserController
 *
 * @author shaw
 * @date 6/24/22 14:57
 */
@RestController
public class Demo1Controller implements Demo1Api {

    @Resource
    private Demo1Service demo1Service;

//    @Value("${test.info}")
    private String info;

    @Override
    public ReturnResult<DemoReponse> getDemo(Long id) {
        System.out.println("info = " + info);
        DemoReponse demo = demo1Service.getDemo(id);
        return new ReturnResult<>(200, "成功", demo);
    }

    @Override
    public ReturnResult deleteDemo(Long id) {
        demo1Service.deleteDemo(id);
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult addDemo(DemoRequest req) {
        DemoEntity entity = CopyUtils.copyObject(req, DemoEntity.class);
        return new ReturnResult<>(200, "成功", demo1Service.add(entity));
    }

    @Override
    public ReturnResult modifyDemo(DemoRequest req) {
        DemoEntity entity = CopyUtils.copyObject(req, DemoEntity.class);
        return new ReturnResult<>(200, "成功", demo1Service.modify(entity));
    }

    @Override
    public ReturnResult incr(Long id, Integer num) {
        return new ReturnResult<>(200, "成功", demo1Service.incr(id, num));
    }

    @Override
    public ReturnResult<?> demo1Header(String language, DemoRequest req) {
        return ReturnResult.success("language = " + language + ", msg = " + req);
    }
}
