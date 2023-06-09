package com.novax.ex.demo.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.request.RedisHashRequest;
import com.novax.ex.demo.open.model.request.RedisListRequest;
import com.novax.ex.demo.open.model.request.RedisStringRequest;
import com.novax.ex.demo.open.model.request.RedisZSetRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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


    @Operation(summary = "list 设置内容", description = "list 设置内容")
    @PostMapping("/list")
    ReturnResult<?> listAdd(@RequestBody RedisListRequest body);
    @Operation(summary = "list 获取内容", description = "list 获取内容")
    @GetMapping("/list")
    ReturnResult<List<Object>> listGet(String key);

    @Operation(summary = "set 设置内容", description = "set 设置内容")
    @PostMapping("/set")
    ReturnResult<?> setAdd(@RequestBody RedisListRequest body);
    @Operation(summary = "set 获取内容", description = "set 获取内容")
    @GetMapping("/set")
    ReturnResult<Set<Object>> setGet(String key);

    @Operation(summary = "z-set 设置内容", description = "z-set 设置内容")
    @PostMapping("/z-set")
    ReturnResult<?> zSetAdd(@RequestBody RedisZSetRequest body);
    @Operation(summary = "z-set 获取内容", description = "z-set 获取内容")
    @GetMapping("/z-set")
    ReturnResult<Set<Object>> zSetGet(String key);

    @Operation(summary = "hash 设置内容", description = "hash 设置内容")
    @PostMapping("/hash")
    ReturnResult<?> hashPut(@RequestBody RedisHashRequest body);
    @Operation(summary = "hash 获取内容", description = "hash 获取内容")
    @GetMapping("/hash")
    ReturnResult<Map<Object, Object>> hashGet(@Parameter(name = "key") String key);

    @Operation(summary = "通用删除方法", description = "通用删除方法")
    @DeleteMapping("/common")
    ReturnResult<?> delete(Set<String> keys);
    @Operation(summary = "通用设置过期时间方法", description = "通用设置过期时间方法")
    @Parameters({
            @Parameter(name = "key", description = "key"),
            @Parameter(name = "expire", description = ""),
            @Parameter(name = "unit", description = "key"),
    })
    @PutMapping("/common/expire")
    ReturnResult<?> expire(String key, Long expire, String unit);

    @Operation(summary = "通用修改方法", description = "通用修改方法")
    @PutMapping("/common")
    ReturnResult<?> rename(String oldKey, String newKey);

    @Operation(summary = "防止重复提交（弱网环境下，或由client端发起的重复请求）", description = "防止重复提交")
    @PostMapping("/recommit")
    ReturnResult<?> recommit(@RequestBody RedisZSetRequest body);
}
