package com.novax.ex.demo.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo.infrastructure.entity.DemoEntity;
import com.novax.ex.demo.open.api.DemoApi;
import com.novax.ex.demo.open.model.request.DemoRequest;
import com.novax.ex.demo.open.model.response.DemoReponse;
import com.novax.ex.demo.provider.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: UserController
 *
 * @author shaw
 * @date 6/24/22 14:57
 */
@RestController
public class DemoController implements DemoApi {

    @Resource
    private DemoService demoService;

    @Override
    public ReturnResult publicGetV1(String arg) {
        demoService.redisLockDemo();
        return new ReturnResult();
    }

    @Override
    public ReturnResult publicGetV2(String arg) {
        demoService.redisLockDemo2();
        return new ReturnResult();
    }

    @Override
    public ReturnResult privateGetV1(String arg) {
        return null;
    }

    @Override
    public ReturnResult privatePostV2(String arg) {
        return null;
    }

    @Override
    public ReturnResult getV1(String arg) {
        return null;
    }

    @Override
    public ReturnResult postV2(String arg) {
        return null;
    }

    @Override
    @SentinelResource(value = "v1_private_demo*", fallback = "handlerFallback")
    public ReturnResult<DemoReponse> getDemo(Long id) {
        DemoReponse demo = demoService.getDemo(id);
        return new ReturnResult<>(200, "成功", demo);
    }

    //本例是fallback
    public ReturnResult<DemoReponse> handlerFallback(@PathVariable Long id, Throwable e) {
        return new ReturnResult<>(500, "fallback方法,exception内容：" + e.getMessage() + id, null);
    }


    @Override
    public ReturnResult deleteDemo(Long id) {
        demoService.deleteDemo(id);
        return new ReturnResult<>(200, "成功");
    }

    @Override
    public ReturnResult modifyDemo(DemoRequest req) {
        DemoEntity entity = CopyUtils.copyObject(req, DemoEntity.class);
        return new ReturnResult<>(200, "成功", demoService.modify(entity));
    }

}
