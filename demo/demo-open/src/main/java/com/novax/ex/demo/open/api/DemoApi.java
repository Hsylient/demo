package com.novax.ex.demo.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.request.DemoRequest;
import com.novax.ex.demo.open.model.response.DemoReponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Description: demo
 *
 * @author shaw
 * @date 6/24/22 14:54
 */
@Tag(name = "demo", description = "demo")
@RequestMapping
public interface DemoApi {

    @Operation(summary = "public v1 demo", description = "无需鉴权接口")
    @GetMapping("/v1/public/demo/get")
    ReturnResult publicGetV1(String arg);

    @Operation(summary = "public v2 demo", description = "无需鉴权接口")
    @GetMapping("/v2/public/demo/get")
    ReturnResult publicGetV2(String arg);

    @Operation(summary = "private v1 demo", description = "内部私有接口")
    @GetMapping("/v1/private/demo/get")
    ReturnResult privateGetV1(String arg);

    @Operation(summary = "private v2 demo", description = "内部私有接口")
    @PostMapping("/v2/private/demo/post")
    ReturnResult privatePostV2(String arg);

    @Operation(summary = "v1 demo", description = "普通鉴权接口")
    @GetMapping("/v1/demo/get")
    ReturnResult getV1(String arg);

    @Operation(summary = "v2 demo", description = "普通鉴权接口")
    @PostMapping("/v2/demo/post")
    ReturnResult postV2(String arg);

    @Operation(summary = "获取信息", description = "测试demo")
    @GetMapping("/v1/private/demo/{id}")
    ReturnResult<DemoReponse> getDemo(@PathVariable Long id);

    @Operation(summary = "删除", description = "测试demo删除")
    @DeleteMapping("/v1/private/demo/{id}")
    ReturnResult deleteDemo(@PathVariable Long id);


    @Operation(summary = "修改（包含分布式事务）", description = "测试demo修改")
    @PutMapping("/v1/private/demo")
    ReturnResult modifyDemo(@RequestBody DemoRequest req);


    @Operation(summary = "内部调用测试-带请求头的请求", description = "带请求头的请求")
    @GetMapping("/v1/public/demo/header")
    ReturnResult<?> testHeader(@RequestHeader("language") String language,
                            @RequestParam String msg);
}