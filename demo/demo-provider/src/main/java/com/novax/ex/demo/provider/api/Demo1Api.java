package com.novax.ex.demo.provider.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.query.DemoQuery;
import com.novax.ex.demo.open.model.request.DemoRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "demo1", contextId = "demo1Api")
public interface Demo1Api {
    @Operation(summary = "新增", description = "测试demo新增")
    @PostMapping("/v1/private/demo1")
    ReturnResult addDemo(@RequestBody DemoRequest req);


    @Operation(summary = "修改", description = "测试demo修改")
    @PutMapping("/v1/private/demo1")
    ReturnResult modifyDemo(@RequestBody DemoRequest req);


    @Operation(summary = "修改", description = "测试demo修改")
    @PutMapping("/v1/private/demo1/incr/{id}")
    ReturnResult incr(@PathVariable("id") Long id,
                      @RequestParam("num") Integer num);


    @Operation(summary = "public v1 demo1", description = "无需鉴权接口")
    @GetMapping("/v1/public/demo1/header")
    ReturnResult<?> demo1Header(@RequestHeader("language") String language,
                                @SpringQueryMap DemoRequest msg);

    @Operation(summary = "GET查询测试用（内部调用）", description = "GET查询测试用（内部调用）")
    @GetMapping("/v1/public/demo1/query")
    ReturnResult<?> demo1Query(@RequestHeader("language") String language,
                               @SpringQueryMap DemoQuery2 req);

    @Operation(summary = "POST查询测试用（内部调用）", description = "POST查询测试用（内部调用）")
    @PostMapping("/v1/public/demo1/query")
    ReturnResult<?> demo1QueryPost(@RequestHeader("language") String language,
                                   @RequestBody DemoQuery req);

    @Operation(summary = "BingingResult测试-Post")
    @PostMapping("/v1/private/demo1/binging-result")
    ReturnResult<?> bingingResultPost(@RequestBody DemoRequest dto);

    @Operation(summary = "BingingResult测试-Get")
    @GetMapping("/v1/private/demo1/binging-result")
    ReturnResult<?> bingingResultGet(@SpringQueryMap DemoRequest dto);

}