package com.ex.demo;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple0;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Description 使用lambda表达式计算相同单词的个数
 * @Author zhaolei
 * @Date 3/1/23 2:55 PM
 * @Version 1.0
 */
public class _04StringWordsCountLambda {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);
        DataStreamSource<String> dataStreamSource = env.readTextFile("/Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile");
        //1.首先将元数据中的每一个数据变成大写或小写
        /*SingleOutputStreamOperator<String> map1 = dataStreamSource.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                return s.toUpperCase();
            }
        });*/
        //使用lambda表达式,因为是单参数和单方法接收返回值的function,所以可以写成这样
        //SingleOutputStreamOperator<String> map = dataStreamSource.map(s -> s.toUpperCase());
        //近一步简写可以使用方法引用
        SingleOutputStreamOperator<String> upperCase = dataStreamSource.map(String::toUpperCase);
        //2.处理数据流
        /*
        SingleOutputStreamOperator<Tuple2<String, Integer>> outputStreamOperator = upperCase.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] split = s.split("\\s+");
                for (String s1 : split) {
                    collector.collect(Tuple2.of(s1, 1));
                }
            }
        });
         */
        //单函数返回使用lambda表达是写法
        SingleOutputStreamOperator<Tuple2<String, Integer>> outputStreamOperator = upperCase.flatMap((String s, Collector<Tuple2<String, Integer>> collector) -> {
            String[] split = s.split("\\s+");
            for (String s1 : split) {
                collector.collect(Tuple2.of(s1, 1));
            }
        })      //使用lambda表达式进行复杂对象返回时泛型T在进行操作的时候无法识别还原，导致会报一个擦除类型异常，flink专门提供returns让其指定返回类型
                //.returns(new TypeHint<Tuple2<String, Integer>>() {})//通过TypeHint返回TypeInformation
                .returns(TypeInformation.of(new TypeHint<Tuple2<String, Integer>>() {}))//直接指定TypeInformation，此方法更常用
                //.returns(Types.TUPLE(Types.STRING,Types.INT))//通过Types工具类的静态方法返回TypeInformation
                ;
        //3.指定算子分组规则
        /*
        KeyedStream<Tuple2<String, Integer>, String> keyedStream = outputStreamOperator.keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
            @Override
            public String getKey(Tuple2<String, Integer> tuple2) throws Exception {
                return tuple2.f0;
            }
        });*/
        //使用lambda表达式简写
        KeyedStream<Tuple2<String, Integer>, String> keyedStream = outputStreamOperator.keyBy(tuple2 -> tuple2.f0);
        //4.计算聚合
        keyedStream.sum(1).print();
        env.execute();
    }
}
