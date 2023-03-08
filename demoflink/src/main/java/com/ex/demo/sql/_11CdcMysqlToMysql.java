package com.ex.demo.sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @Description 借助cdc 实现将计算的结果集实时在放入到mysql结果集表
 * @Author zhaolei
 * @Date 3/8/23 8:36 PM
 * @Version 1.0
 */
public class _11CdcMysqlToMysql {
    public static void main(String[] args) {
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
        //创建jdbc 连接表
        tableEnv.executeSql("CREATE TABLE flink_spot_sum (\n" +
                "  symbol STRING,\n" +
                "  price decimal(32,16),\n" +
                "  amount decimal(32,12),\n" +
                "  PRIMARY KEY (symbol) NOT ENFORCED\n" +
                ") WITH (\n" +
                "   'connector' = 'jdbc',\n" +
                "   'url' = 'jdbc:mysql://192.168.10.245:3306/superex_spot',\n" +
                "   'username' = 'root',\n" +
                "   'password' = 'TS_mysql245',\n" +
                "   'table-name' = 'flink_spot_tmp'\n" +
                ")");


        tableEnv.executeSql("insert into flink_spot_sum select symbol,sum(price) price,sum(amount) amount from flink_spot_order group by symbol");

    }
}
