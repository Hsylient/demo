# Readme

## 初始化数据
执行 init.sql

## 配置
```yaml
# spring boot 应用配置
seata:
  enabled: true
  application-id: ${spring.application.name}
  tx-service-group: default-tx-group
  service:
    vgroup-mapping:
      default-tx-group: default
  config:
    type: nacos
    nacos:
      serverAddr: 127.0.0.1:8848
      dataId: "seata.properties"
      username: 'nacos'
      password: 'nacos'
  registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      username: 'nacos'
      password: 'nacos'
```
```properties
# 注册中心配置 business, DEFAULT_GROUP
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/seata_stock?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
spring.datasource.username=root
spring.datasource.password=root
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.mapper-locations=classpath*:mapper/*.xml
```
```yaml
# 注册中心配置 seata.properties，SEATA_GROUP，增加以下配置
service.vgroupMapping.default-tx-group=default
```

## 测试
http://localhost:8081/v3/public/demo/seata/business/placeOrder/commit
http://localhost:8081/v3/public/demo/seata/business/placeOrder/rollback?id=1
http://localhost:8081/v3/public/demo/seata/business/updateOrder

## 附
1. 使用 @GlobalTransactional 开启开局事务；
2. 使用 @LocalTCC 和 @TwoPhaseBusinessAction 开启分支事务；