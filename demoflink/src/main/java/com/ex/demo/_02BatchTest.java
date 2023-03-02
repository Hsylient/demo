package com.ex.demo;

import com.sun.xml.internal.ws.api.pipe.Tube;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @Description flink 批计算
 * @Author zhaolei
 * @Date 2/27/23 3:32 PM
 * @Version 1.0
 */
public class _02BatchTest {
    public static void main(String[] args) throws Exception{
        //创建批计算执行入口
        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();
        //指定读取数据源
        DataSource<String> stringDataSource = environment.readTextFile("/Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile");
        //map() 和 mapPartition() 的区别 map是每做一次就调用一次函数，mapPartition是每个分区调用一次，一次性给一个迭代器自己去处理
        //mapPartition 应用场景比如需要连接外部资源去处理一些东西，比如连接数据库，这个事情希望是连接一次，处理很多数据
        //stringDataSource.map()
        //stringDataSource.mapPartition()
        //处理数据源
        FlatMapOperator<String, Tuple2<String, Integer>> stringTuple2FlatMapOperator = stringDataSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                //按照接收的数据进行空格分数
                String[] split = s.split("\\s+");
                for (String s1 : split) {
                    //将数据放入元组，声明的是一个2个值的元组，第一个放入的是要统计的数据，第二个是定义的一个值，
                    collector.collect(Tuple2.of(s1, 1));
                }
            }
        });
        AggregateOperator<Tuple2<String, Integer>> sum = stringTuple2FlatMapOperator.groupBy(0).sum(1);
        //4.通过sink获取结果输出
        sum.print();
        //stringTuple2FlatMapOperator.print();
        //5.触发事件提交运行
        //environment.execute();
    }
}
