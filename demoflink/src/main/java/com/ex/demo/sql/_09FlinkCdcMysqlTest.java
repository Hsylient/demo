package com.ex.demo.sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @Description flink cdc 的使用
 * @Author zhaolei
 * @Date 3/7/23 3:52 PM
 * @Version 1.0
 */
public class _09FlinkCdcMysqlTest {
    public static void main(String[] args) {
/*
        `id` bigint(20) NOT NULL,
        `user_id` bigint(20) NOT NULL COMMENT '用户id',
                `symbol` varchar(32) NOT NULL COMMENT '交易对',
                `side` int(2) NOT NULL COMMENT '交易类型:1=买入,2=卖出',
                `price` decimal(32,16) NOT NULL COMMENT '价格',
                `amount` decimal(32,12) NOT NULL COMMENT '订单交易量（市价买单为订单交易额）',
                `trade_amount` decimal(32,12) NOT NULL DEFAULT '0.000000000000' COMMENT '成交量',
                `trade_turnover` decimal(32,20) NOT NULL DEFAULT '0.00000000000000000000' COMMENT '成交金额',
                `fee` decimal(32,20) NOT NULL DEFAULT '0.00000000000000000000' COMMENT '已成交手续费',
                `order_type` int(2) NOT NULL DEFAULT '1' COMMENT '订单类型1=现价单2=市价单',
                `source` int(2) NOT NULL DEFAULT '1' COMMENT '订单来源1:服务2:api',
                `state` int(2) NOT NULL COMMENT '订单状态:1=待成交,2=部分成交,3=已成交,4=撤销申请中,5=部分成交撤销,6=已撤销',
                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
*/

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000);
        env.getCheckpointConfig().setCheckpointStorage("file:///Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile/checkpoint");
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        tableEnv.executeSql("CREATE TABLE flink_spot_order (\n" +
                "    id bigint,\n" +
                "    user_id bigint,\n" +
                "    symbol STRING,\n" +
                "    side int,\n" +
                "    price decimal(32,16),\n" +
                "    amount decimal(32,12),\n" +
                "    trade_amount decimal(32,12),\n" +
                "    trade_turnover decimal(32,20),\n" +
                "    fee decimal(32,20),\n" +
                "    order_type int,\n" +
                "    source int,\n" +
                "    state int,\n" +
                "    create_time TIMESTAMP,\n" +
                "    update_time TIMESTAMP,\n" +
                "    PRIMARY KEY (id) NOT ENFORCED\n" +
                "  ) WITH (\n" +
                "    'connector' = 'mysql-cdc',\n" +
                "    'hostname' = '192.168.10.245',\n" +
                "    'port' = '3306',\n" +
                //"    'username' = 'superex',\n" +
                //"    'password' = 'TS_superex245',\n" +
                "    'username' = 'root',\n" +
                "    'password' = 'TS_mysql245',\n" +
                "    'database-name' = 'superex_spot',\n" +
                "    'table-name' = 'spot_order'\n" +
                "  )");
        tableEnv.executeSql("select * from flink_spot_order").print();

    }
}
