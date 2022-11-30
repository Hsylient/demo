## 腾狮技术开发规范
------------
### 1 java编码规范
* 1.1 类名使用UpperCamelCase风格,例：DemoApi,DemoController...
* 1.2 方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格,例:demoValue,getDemoList()
* 1.3 常量命名全部大写，单词间用下划线隔开,必要时需要注释用途与意义,例:USER_TYPE=1
* 1.4 抽象类命名使用Abstract或Base开头,让其他开发人员一眼就能认出来这是一个抽象类，例：AbstractDemoDao,BaseDemoDao
* 1.5 包名统一使用小写，例：com.novax.demo
* 1.6 避免在子父类的成员变量之间取相同的名字，防止造成误解，从而降低可读性
* 1.7 枚举类使用Enum后缀，枚举成员名称需要全大写，单词间用下划线隔开。
* 1.8 常量/枚举要按照功能进行归类，分开维护，方便其他同学
* 1.9 POJO类属性必须使用包装数据类型,RPC方法的返回值和参数使用包装数据类型
* 1.10 类、类方法的注释规范，尽可能使用多行注释格式，减少使用单行注释方式。
* 1.11 所有的抽象方法（包括接口中的方法）必须要注释,必须指出该方法做什么事情，实现什么功能,若处理业务复杂请尽可能详细。
* 1.12 TODO的使用请记录清楚未完成的工作，需要谁提供之后才能进行等进行标记，以便后期补充业务时产生遗漏
* 1.13 日志统一使用log4j日志输出，方便后期日志收集系统采集和改造

### 2 mysql编码规范
* 2.1 表名、字段名必须使用小写字母或数字，禁止出现数字开头，禁止两个下划线中间只出现数字
* 2.2 禁止使用float和double
* 2.3 表的命名最好是遵循业务取名
* 2.4 如果以_tmp结尾是告知DBA可以随便删除与业务无关的
* 2.5 统计查询时SQL性能优化至少要达到range级别，要求是ref级别，如果可以是consts最好
* 2.6 建表必须有表说明，字段必须有说明，如果字段代表的是状态或不同类型业务走向，必须枚举出详细类型信息
 
### 3 腾狮微服务开发规范
#### 3.1 open模块
* 3.1.1 api:规范命名是按照业务功能/表名创建，路径/v3/demo或者/v3/private/demo或者/v3/public/demo(private代表此接口为私有不对外提供在网管层会进行过滤,public代表此接口无需用户登录可以直接进行访问)，方便其他开发人员对其进行维护，例子如下：
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@Tag(name = "系统名称-模块名称-子模块名称", description = "系统名称-模块名称")
@RequestMapping(value = "/v3/demo")
public interface DemoApi {
    @Operation(summary = "列表")
    @GetMapping("page")
    ReturnResult<PageUtil<?>> page(@ParameterObject DemoQuery query);

    @Operation(summary = "新增")
    @PostMapping
    ReturnResult<?> add(@RequestBody DemoRequest body);

    @Operation(summary = "编辑")
    @PutMapping
    ReturnResult<?> modify(@RequestBody DemoRequest body);
}
```
* 3.1.2 model:模块氛围query,request,response三个子模块：
* query模块
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "功能简述 query")
@Data
public class DemoQuery extends PageQuery {
    @Parameter(description = "查询字段描述，摘产品文档上的文字条件")
    private Long id;
}
```
* request模块(用于接收前台post数据接收)
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "功能简述 request")
@Data
public class DemoRequest extends BaseEntity {
    @Schema(description = "字段描述，摘产品文档上")
    private Long name;
}
```
* response模块(用于返回数据接收)
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "功能简述 Response")
@Data
public class DemoResponse extends BaseEntity {
    @Schema(description = "字段描述，摘产品文档上")
    private Long name;
}
```
#### 3.2 provider模块
* 3.2.1 api:用来存放调用其他微服务的接口，例子如下：
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@FeignClient(value = ServerName.BASICS, contextId = "InternationalApi")
public interface InternationalApi {
    /**
     *
     * Description: 获取国际化服务对应的翻译
     *
     * @param code 国际化code
     * @param lan 语言
     * @return com.novax.ex.common.results.ReturnResult<java.lang.String>
     * @author shaw
     * @date 9/16/22 2:57 PM
     */
    @GetMapping(value = "/international/get")
    ReturnResult<String> getInternational(@RequestParam("code") String code, @RequestParam("lan") String lan);
}
```
* 3.2.2 common:用来存放模块使用的工具类，常量，公共类等：

* 3.2.3 config:其他第三方插件的配置类

* 3.2.4 controller:实现open中定义的接口层
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@RestController
public class DemoController implements DemoApi {
    @Resource
    DemoService demoService;
    @Override
    public ReturnResult<PageUtil<?>> page(Long userId, String language, DemoQuery query) {
        PageUtil<?> pageInfo = new PageUtil<>(query);
        pageInfo.setCondition(Collections.singletonMap("query", query));
        demoService.findPage(pageInfo, DemoResponse.class);
        return ReturnResult.success(pageInfo);
    }
}
```
* 3.2.4 handler:rabbitmq消息的发送者(provider)，接收者(consumer)

* 3.2.5 service:提供给controller层调用的实现，本层调用DAO层
```
/**
 * 功能描述
 * @author 作者
 * @date 2022-01-01 10:00:00
 * @Version 1.0
 */
@Slf4j
@Service
public class DemoService extends BaseService {
    @Resource
    DemoMapper demoMapper;

    @Override
    protected BaseMapper getBaseMapper() {
        return demoMapper;
    }
    
    public ReturnResult<PageUtil<DemoResponse>> getPage(Long userId, DemoQuery query) {
        PageUtil<DemoResponse> page = new PageUtil<>(query);
        Map<String, Object> condition = new HashMap<>();
        condition.put("query", query);
        page.setCondition(condition);
        this.findPage(page, DemoResponse.class);
        return ReturnResult.success(page);
    }
}
```


