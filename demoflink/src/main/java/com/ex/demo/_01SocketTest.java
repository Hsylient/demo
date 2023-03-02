package com.ex.demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Description 接收数据测试flink环境
 * @Author zhaolei
 * @Date 2/8/23 3:09 PM
 * @Version 1.0
 */
public class _01SocketTest {
    public static void main(String[] args) throws Exception{
        //本demo需要完成一个接收从socket服务发送的数据，将数据接收到之后，计算发送数据按照空格分组后，算出个数"
        //1,创建流计算执行入口
        //StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();//流批一体入口
        //创建一个本地运行环境带webui的
        Configuration configuration = new Configuration();
        configuration.setInteger("rest.port",8080);//设置自定义端口
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);//带webui页面的执行入口
        environment.setParallelism(5);//设置并行度，如果不设置，默认是取本机的物理核心线程数
        //2.通过原始resource数据，创建一个dataStream
        DataStreamSource<String> dataStream = environment.socketTextStream("127.0.0.1", 9999);
        //3.执行算子对数据处理转化-计算代码
        //3.1处理数据流
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator = dataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                //按照接收的数据进行空格分数
                String[] split = s.split("\\s+");
                for (String s1 : split) {
                    //将数据放入元组，声明的是一个2个值的元组，第一个放入的是要统计的数据，第二个是定义的一个值，
                    collector.collect(Tuple2.of(s1, 1));
                }
            }
        }).setParallelism(5);//可以单独设置并行度
        //3.2执行分组按照元组中的哪个进行分组
        KeyedStream<Tuple2<String, Integer>, String> keyedStream = streamOperator.keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
            @Override
            public String getKey(Tuple2<String, Integer> tuple2) throws Exception {
                return tuple2.f0;
            }
        });
        //3.3统计数据,注意取的是上面定义的元组的下标为1的数据
        SingleOutputStreamOperator<Tuple2<String, Integer>> resultStream = keyedStream.sum(1);
        //4.通过sink获取结果输出
        resultStream.print("resultStream");
        streamOperator.print("oldDataStream");
        //5.触发事件提交运行
        environment.execute();
    }
}
