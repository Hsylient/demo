package com.ex.demo;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Description
 * @Author zhaolei
 * @Date 2/27/23 5:41 PM
 * @Version 1.0
 */
public class _03StringBatchTest {
    public static void main(String[] args) throws Exception {
        //创建流批一体的执行入口
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();//流批一体入口
        //将流处理执行方式通过模式设置为批处理的模式，这样流处理的方式就变为批处理的方式，这样就实现写一套代码既能支持批计算诉求，也能执行流计算，无需写两套程序处理逻辑代码
        environment.setRuntimeMode(RuntimeExecutionMode.BATCH);//批计算
        //environment.setRuntimeMode(RuntimeExecutionMode.STREAMING);//流计算
        //environment.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);//默认执行模式，让程序自己判断
        DataStreamSource<String> stringDataStreamSource = environment.readTextFile("/Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile");
        SingleOutputStreamOperator<Tuple2<String, Integer>> outStream = stringDataStreamSource.flatMap(new myFlatMap());
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = outStream.keyBy(new myKeySelect()).sum(1);
        //outStream.print();
        sum.print();
        environment.execute();
    }
}
class myFlatMap implements FlatMapFunction<String, Tuple2<String, Integer>>{
    @Override
    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
        String[] split = s.split("\\s+");
        for (String s1 : split) {
            //将数据放入元组，声明的是一个2个值的元组，第一个放入的是要统计的数据，第二个是定义的一个值，
            collector.collect(Tuple2.of(s1, 1));
        }
    }
}
class myKeySelect implements KeySelector<Tuple2<String, Integer>,String>{
    @Override
    public String getKey(Tuple2<String, Integer> tuple2) throws Exception {
        return tuple2.f0;
    }
}
