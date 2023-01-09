package com.novax.ex.demo.provider.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo.infrastructure.entity.DemoEntity;
import com.novax.ex.demo.open.api.DemoApi;
import com.novax.ex.demo.open.model.query.DemoQuery;
import com.novax.ex.demo.open.model.request.DemoRequest;
import com.novax.ex.demo.open.model.response.DemoReponse;
import com.novax.ex.demo.provider.api.Demo1Api;
import com.novax.ex.demo.provider.api.DemoQuery2;
import com.novax.ex.demo.provider.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.ZoneOffset;

/**
 * Description: UserController
 *
 * @author shaw
 * @date 6/24/22 14:57
 */
@Slf4j
@RestController
public class DemoController implements DemoApi {

    @Resource
    private DemoService demoService;
    @Resource
    private Demo1Api demo1Api;

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

    @Override
    public ReturnResult<?> testHeader(String language, DemoRequest req) {
        ReturnResult<?> res = demo1Api.demo1Header(language, req);
        log.info("res = {}", res);
        return res;
    }

    @Override
    public ReturnResult<?> testQuery(String language, DemoQuery req) {
        log.info("lan={}, param={}", language, req);
        ReturnResult<?> resPost = null;
        try {
            resPost = demo1Api.demo1QueryPost(language, req);
        } catch (Exception e) {
            log.info("err2", e);
        }
        log.info("resPost = {}", resPost);

        ReturnResult<?> resGet = null;
        try {
            // get 请求，date,localDateTime会有问题
            DemoQuery2 req2 = BeanUtil.copyProperties(req, DemoQuery2.class);
            req2.setDate(req.getDate().getTime());
            req2.setLocalDateTime(req.getLocalDateTime()
                    .toInstant(ZoneOffset.of("+8")).toEpochMilli());
            resGet = demo1Api.demo1Query(language, req2);
        } catch (Exception e) {
            log.info("err", e);
        }
        log.info("resGet = {}", resGet);
        return ReturnResult.success(resGet + ", " + resPost);
    }

    @Override
    public ReturnResult<?> bingingResult(HttpServletRequest request, DemoRequest dto) {
        log.info("req:{}", dto);
        ReturnResult<?> returnResult = demo1Api.bingingResult(dto);
        log.info("res1:{}", returnResult);
        ReturnResult<?> returnResult2 = demo1Api.bingingResultGet(dto);
        log.info("res2:{}", returnResult2);
        return ReturnResult.success(returnResult + ", " + returnResult2);
    }


}
