package com.ex.demo.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Description 流变表
 * @Author zhaolei
 * @Date 3/8/23 11:25 AM
 * @Version 1.0
 */
public class _10StreamToTable {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        //获取源文件数据-这个数据可以随便指定读取
        DataStreamSource<String> streamSource = env.readTextFile("/Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile/sql/user.txt");
        //数据通过算子处理成对象数据
        //SingleOutputStreamOperator<User> operator = streamSource.flatMap(new myFlatMap());
        SingleOutputStreamOperator<User> map = streamSource.map(new myMap());
        //使用自定义对象流数据一定要注意有一个点，自定义对象是public static 修饰的，因为源码需要根据传入的对象进行数据访问映射出表的column名称，如果不设置为public 默认是protected不能由外部包进行访问会导致映射失败
        tableEnv.createTemporaryView("tbl_user", map);//直接创建带表名的table, 流变表
        //Table table = tableEnv.fromDataStream(operator);//直接使用dataStream创建table对象,后续直接使用api进行查询
        //tableEnv.executeSql("select * from tbl_user").print();
        //需求：按照sex分组，并找出年龄最大的两个人
        //第一种实现：调用executeSql继续创建结果集视图表，然后在执行查询视图表
        /*
         tableEnv.executeSql("CREATE TEMPORARY VIEW IF NOT EXISTS tmp as " +
                "SELECT * FROM (select id,name,sex,age," +
                "ROW_NUMBER() OVER (PARTITION BY sex ORDER BY age DESC) AS row_num " +
                "from tbl_user ) " +
                "WHERE row_num <= 2");
        tableEnv.executeSql("select * from tmp").print();//查询结果集
        tableEnv.toChangelogStream(tableEnv.from("tmp")).print();//将结果集变为dataStream
         */
        //第二种实现：使用Table然后转临时表
        String sql = "SELECT * FROM (select id,name,sex,age," +
                "ROW_NUMBER() OVER (PARTITION BY sex ORDER BY age DESC) AS row_num " +
                "from tbl_user ) " +
                "WHERE row_num <= 2";
        Table table = tableEnv.sqlQuery(sql);//转换为
        tableEnv.createTemporaryView("tmp", table);
        tableEnv.executeSql("select * from tmp").print();//查询结果集
        tableEnv.toChangelogStream(tableEnv.from("tmp")).print();//将结果集变为dataStream 表变流
        env.execute();
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private Integer id;
        private String name;
        private String sex;
        private Integer age;
    }

    public static class myFlatMap implements FlatMapFunction<String, User> {
        @Override
        public void flatMap(String s, Collector<User> collector) throws Exception {
            String[] split = s.split(",");
            collector.collect(new User(Integer.parseInt(split[0]), split[1], split[2], Integer.parseInt(split[3])));
        }
    }

    public static class myMap implements MapFunction<String, User> {
        @Override
        public User map(String s) throws Exception {
            String[] split = s.split(",");
            return new User(Integer.parseInt(split[0]), split[1], split[2], Integer.parseInt(split[3]));
        }
    }
}




