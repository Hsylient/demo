package com.novax.ex.demo.provider.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.request.DemoRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
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
                               @RequestParam DemoRequest msg);
}