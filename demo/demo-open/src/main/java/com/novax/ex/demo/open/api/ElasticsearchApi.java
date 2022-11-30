package com.novax.ex.demo.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.request.EsRequest;
import com.novax.ex.demo.open.model.response.EsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * elasticsearch demo
 *
 * @author zhenghao
 * @date 2022/8/23 10:29
 */
@Tag(name = "es", description = "es demo")
@RequestMapping(value = "/v1/private/es")
public interface ElasticsearchApi {
//    @Operation(summary = "获取所有", description = "获取所有信息")
//    @GetMapping
//    ReturnResult<List<EsResponse>> list();
//
//    @Operation(summary = "根据id获取", description = "根据id获取信息")
//    @GetMapping("/{id}")
//    ReturnResult<EsResponse> findById(@Parameter(description = "查询id", required = true) @PathVariable String id);
//
//    @Operation(summary = "新增", description = "新增数据")
//    @PostMapping
//    ReturnResult<Boolean> add(@RequestBody EsRequest request);
//
//    @Operation(summary = "修改", description = "修改数据")
//    @PutMapping
//    ReturnResult<Boolean> modify(@RequestBody EsRequest request);
//
//    @Operation(summary = "删除", description = "删除数据")
//    @Parameter(name = "id", description = "要删除的id", required = true)
//    @DeleteMapping("/{id}")
//    ReturnResult<Boolean> remove(@PathVariable String id);
}
