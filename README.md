## swagger: [http://ip:port/doc.html](http://ip:port/doc.html)
* 完整的数字货币交易系统是由
* 用户系统(sso)、账户系统(account)、订单系统(order)、撮合系统(match)、以及清算系统( clearing )、行情系统(market)和钱包系统(wallet)构成的。
* 各个子系统相互配合，完成数字货币报价交易。
* gateway：51001   网关
* gateway：51002   后台网关
* admin：51003   后管
* user：51004   用户
* basics：51005   基础服务
* otc：51006   C2C
* wallet：51007   钱包
* broker：51008   代理商/节点计划系统
* internation：51009   国际化
* HD：51010   HD
* activity：51011 活动
* resource：51012 资源服务
* account：51013 账户
* common：51014 公共配置
* xxljob-admin: 51015 xxljob-admin
* xxljob-service: 51016 xxljob-service
* socket：51017   socket收发系统
* socket：51018   socket用户分发
* activity-other：51019 其他活动(非长期活动)

* spot-order：51040   现货系统
* spot-match：51041   现货撮合
* spot-clearing：51042   现货结算/清算系统
* spot-market：51043   现货行情
* spot-other-market：51044   现货行情采集
* spot-produce-market：51045   现货行情生产
* spot-task：51046   现货定时任务
* spot-hedge：51047   现货对冲系统
* spot-socket：51048   socket收发系统

* swap-order：51060   U本位合约系统
* swap-match：51061   合约撮合
* swap-clearing：51062   合约结算/清算系统
* swap-settlement：51063   合约爆仓结算
* swap-market：51064   合约行情
* swap-other-market：51065   合约行情采集
* swap-produce-market：51066   合约行情生产
* swap-task：51067   合约定时任务
* swap-socket：51068   socket收发系统

* crypot：51080   币本位合约系统
* crypot-match：51081   合约撮合
* crypot-clearing：51082   合约结算/清算系统
* crypot-settlement：51083   合约爆仓结算
* crypot-market：51084   合约行情
* crypot-other-market：51085   合约行情采集
* crypot-produce-market：51086   合约行情生产
* crypot-task：51087   合约定时任务
* crypot-socket：51088   socket收发系统




* bigdata：51099   大数据分析系统
* service：51099   客服系统系统

* service：51099   理财系统

* demo：51099   demo
* demo1：51099   demo1

## NACOS(服务&配置管理):
* 控制台: http://192.168.10.226:8848/nacos
## mq可视化页面地址
* http://192.168.10.226:15672/#/

## 内网测试地址:  
* 前台:
* 后台:

## Dev测试地址:
* 前台
* 后台

## 设计图/原型图:
* 前台: 
1. 原型图:
2. 设计图(APP):
3. 设计图(PC):
* 后台: 
1. 原型图:

## 开发规范:
* 接口规范
* 设计规范
* 

## 项目进度&需求文档:
* 

## 纪要:
* 

##集群环境地址说明：



## spring doc替换swagger注解：
```
* @Api -> @Tag
* @ApiIgnore -> @Parameter(hidden = true) or @Operation(hidden = true) or @Hidden
* @ApiImplicitParam -> @Parameter
* @ApiImplicitParams -> @Parameters
* @ApiModel -> @Schema
* @ApiModelProperty(hidden = true) -> @Schema(hidden = true)
* @ApiModelProperty -> @Schema
* @ApiOperation(value = "foo", notes = "bar") -> @Operation(summary = "foo", description = "bar")
* @ApiParam -> @Parameter
* @ApiResponse(code = 404, message = "foo") -> @ApiResponse(responseCode = "404", description = "foo")
```
