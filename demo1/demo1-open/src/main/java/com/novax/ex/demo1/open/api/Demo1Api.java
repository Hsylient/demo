package com.novax.ex.demo1.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo1.open.model.query.DemoQuery;
import com.novax.ex.demo1.open.model.query.DemoQuery2;
import com.novax.ex.demo1.open.model.request.DemoRequest;
import com.novax.ex.demo1.open.model.response.DemoReponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * Description: demo
 *
 * @author shaw
 * @date 6/24/22 14:54
 */
@Tag(name = "demo1", description = "demo1")
@RequestMapping
public interface Demo1Api {
    @Operation(summary = "获取信息", description = "测试demo")
    @GetMapping("/v1/private/demo1/{id}")
    ReturnResult<DemoReponse> getDemo(@PathVariable Long id);

    @Operation(summary = "删除", description = "测试demo删除")
    @DeleteMapping("/v1/private/demo1/{id}")
    ReturnResult deleteDemo(@PathVariable Long id);


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


    @Operation(summary = "public v1 demo", description = "无需鉴权接口")
    @GetMapping("/v1/public/demo1/header")
    ReturnResult<?> demo1Header(@RequestHeader("language") String language,
                                @ParameterObject DemoRequest req);


    @Operation(summary = "GET查询测试用（内部调用）", description = "GET查询测试用（内部调用）")
    @GetMapping("/v1/public/demo1/query")
    ReturnResult<?> demo1Query(@RequestHeader("language") String language,
                               @ParameterObject DemoQuery2 req);

    @Operation(summary = "POST查询测试用（内部调用）", description = "POST查询测试用（内部调用）")
    @PostMapping("/v1/public/demo1/query")
    ReturnResult<?> demo1QueryPost(@RequestHeader("language") String language,
                                   @RequestBody DemoQuery req);
}