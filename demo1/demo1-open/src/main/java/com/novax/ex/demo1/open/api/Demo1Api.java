package com.novax.ex.demo1.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo1.open.model.request.DemoRequest;
import com.novax.ex.demo1.open.model.response.DemoReponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
}