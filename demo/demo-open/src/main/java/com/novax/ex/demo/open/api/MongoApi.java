package com.novax.ex.demo.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.request.MongoRequest;
import com.novax.ex.demo.open.model.response.MongoRepose;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description: mongoDB使用案例
 *
 * @author my.miao
 * @date 6/24/22 14:54
 */
@Tag(name = "mongoDB-demo", description = "mongoDB-demo")
@RequestMapping
public interface MongoApi {
    //接口基本访问协议：get(获取)，post(新增)，put(修改)和delete(删除)
    //get /users：列出所有用户
    //get /users/id：根据id获取用户
    //post /user：新增用户
    //put /user/id：根据用户id更新用户
    //delete /user/id：根据用户id删除用户

    @Operation(summary = "根据id获取", description = "根据id获取信息")
    @GetMapping(value = "/v1/private/mongo/{id}")
    ReturnResult<MongoRepose> getMongo(
            @Parameter(description = "测试id", required = true) @PathVariable Long id);

    @Operation(summary = "获取所有", description = "获取所有信息")
    @GetMapping(value = "/v1/private/mongos")
    ReturnResult<List<MongoRepose>> getMongos();

    @Operation(summary = "获取所有", description = "获取所有信息")
    @GetMapping(value = "/v1/private/mongo/page")
    ReturnResult<List<MongoRepose>> page();

    @Operation(summary = "根据交易市场获取", description = "根据交易市场获取信息")
    @Parameter(name = "symbol", description = "交易市场", required = true)
    @GetMapping("/v1/private/mongo/symbol/{symbol}")
    ReturnResult<List<MongoRepose>> getMongoBySymbol(@PathVariable String symbol);

    @Operation(summary = "新增", description = "新增数据")
    @PostMapping(value = "/v1/private/mongo")
    ReturnResult mongoPost(@RequestBody MongoRequest request);

    @Operation(summary = "修改", description = "修改数据")
    @Parameter(name = "id", description = "要修改的id", required = true)
    @PutMapping(value = "/v1/private/mongo/{id}")
    ReturnResult mongoPut(@PathVariable Long id, @RequestBody MongoRequest request);

    @Operation(summary = "删除", description = "删除数据")
    @Parameter(name = "id", description = "要删除的id", required = true)
    @DeleteMapping(value = "/v1/private/mongo/{id}")
    ReturnResult mongoDelete(@PathVariable Long id);
}