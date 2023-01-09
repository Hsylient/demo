package com.novax.ex.demo1.provider.controller;

import com.alibaba.fastjson.JSON;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.demo1.infrastructure.entity.DemoEntity;
import com.novax.ex.demo1.open.model.query.DemoQuery;
import com.novax.ex.demo1.open.model.query.DemoQuery2;
import com.novax.ex.demo1.provider.service.Demo1Service;
import com.novax.ex.demo1.open.api.Demo1Api;
import com.novax.ex.demo1.open.model.request.DemoRequest;
import com.novax.ex.demo1.open.model.response.DemoReponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Description: UserController
 *
 * @author shaw
 * @date 6/24/22 14:57
 */
@Slf4j
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

    @Override
    public ReturnResult<?> demo1Query(String language, DemoQuery2 req) {
        return ReturnResult.success("GET language = " + language
                + ", msg = " + req
                + ", json = " + JSON.toJSONString(req));
    }

    @Override
    public ReturnResult<?> demo1QueryPost(String language, DemoQuery req) {
        return ReturnResult.success("POST language = " + language
                + ", msg = " + req
                + ", json = " + JSON.toJSONString(req));
    }

    @Override
    public ReturnResult<?> bingingResult(DemoRequest dto, BindingResult valid) {
        log.info("req={}", dto);
        if (valid.hasErrors()) {
            String message = Objects.requireNonNull(valid.getFieldError()).getDefaultMessage();
            log.info("demo1 表单提交有误：{}", message);
            return ReturnResult.fail(message);
        }
        return ReturnResult.success(dto);
    }


    @Override
    public ReturnResult<?> bingingResultGet(DemoRequest dto, BindingResult valid) {
        log.info("req={}", dto);
        if (valid.hasErrors()) {
            String message = Objects.requireNonNull(valid.getFieldError()).getDefaultMessage();
            log.info("demo1 表单提交有误：{}", message);
            return ReturnResult.fail(message);
        }
        return ReturnResult.success(dto);
    }
}
