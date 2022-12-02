package com.novax.ex.demo.open.api;

import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.open.model.query.MongoDemoQuery;
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

    @Operation(summary = "新增", description = "新增数据")
    @PostMapping(value = "/v1/private/mongo")
    ReturnResult mongoPost(@RequestBody MongoRequest request);

    @Operation(summary = "修改", description = "修改数据")
    @Parameter(name = "id", description = "要修改的id", required = true)
    @PutMapping(value = "/v1/private/mongo/{id}")
    ReturnResult mongoPut(@PathVariable Long id, @RequestBody MongoRequest request);

    @Operation(summary = "修改指定字段", description = "修改指定字段")
    @PostMapping(value = "/v1/private/mongo/updateColumn")
    ReturnResult updateColumn(@RequestBody MongoRequest request);

    @Operation(summary = "删除", description = "删除数据")
    @Parameter(name = "id", description = "要删除的id", required = true)
    @DeleteMapping(value = "/v1/private/mongo/{id}")
    ReturnResult mongoDelete(@PathVariable Long id);

    //1.简单查询，2.and查询，3.and or 查询 ，4.模糊查询，5.分页查询，6.排序，7.索引 8.聚合其他想到了在补
    @Operation(summary = "简单查询", description = "简单查询")
    @GetMapping("/v1/private/mongo/query")
    ReturnResult getMongoQuery(@RequestBody MongoDemoQuery query);

    @Operation(summary = "and查询", description = "and查询")
    @GetMapping("/v1/private/mongo/queryAnd")
    ReturnResult getMongoQueryAnd(@RequestBody MongoDemoQuery query);

    @Operation(summary = "and or 查询", description = "and or 查询")
    @GetMapping("/v1/private/mongo/queryAndOr")
    ReturnResult getMongoQueryAndOr(@RequestBody MongoDemoQuery query);

    @Operation(summary = "模糊查询", description = "模糊查询")
    @GetMapping("/v1/private/mongo/queryLike")
    ReturnResult getMongoQueryLike(@RequestBody MongoDemoQuery query);

    @Operation(summary = "分页获取", description = "分页获取")
    @GetMapping(value = "/v1/private/mongo/page")
    ReturnResult page(@RequestBody MongoDemoQuery query);

    @Operation(summary = "排序", description = "排序")
    @GetMapping(value = "/v1/private/mongo/sort")
    ReturnResult sort(@RequestBody MongoDemoQuery query);

    @Operation(summary = "索引", description = "索引")
    @GetMapping(value = "/v1/private/mongo/index")
    ReturnResult index(String keyName,Integer sort);

    @Operation(summary = "聚合", description = "聚合")
    @GetMapping(value = "/v1/private/mongo/sum")
    ReturnResult sum(@RequestBody MongoDemoQuery query);
}
