package com.novax.ex.demo.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.request.RedisListRequest;
import com.novax.ex.demo.open.model.request.RedisStringRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Description: redis使用案例
 *
 * @author my.miao
 * @date 2022/11/30 17:06
 */
@Tag(name = "redis-demo", description = "redis-demo")
@RequestMapping("/v1/private/redis")
public interface RedisApi {

    @Operation(summary = "string 设置内容", description = "string 设置内容")
    @PostMapping("/string")
    ReturnResult<?> stringSet(@RequestBody RedisStringRequest body);

    @Operation(summary = "string 获取", description = "string 获取")
    @GetMapping("/string")
    ReturnResult<?> stringGet(@RequestParam("key") String key);


    @Operation(summary = "string 设置内容", description = "string 设置内容")
    @PostMapping("/string")
    ReturnResult<?> listAdd(@RequestBody RedisListRequest body);

    @Operation(summary = "通用删除方法", description = "通用删除方法")
    @DeleteMapping("/common")
    ReturnResult<?> delete(String key);


}
