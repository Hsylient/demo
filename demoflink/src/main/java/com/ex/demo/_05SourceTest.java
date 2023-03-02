package com.ex.demo;

import org.apache.flink.api.common.io.FileInputFormat;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description source 算子代码
 * @Author zhaolei
 * @Date 3/1/23 6:39 PM
 * @Version 1.0
 */
public class _05SourceTest {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        //单个的
        DataStreamSource<String> dataStreamSource = environment.fromElements("a", "b", "c");
        dataStreamSource.map(String::toUpperCase).print("string");
        //集合的
        List<Integer> integers = Arrays.asList(1, 1, 2, 3, 4);
        DataStreamSource<Integer> integerDataStreamSource = environment.fromCollection(integers);//点击源码可以看见只有一个并行度
        integerDataStreamSource.map(i->i*10).print("integer");
        //序列
        //environment.fromSequence(1l,1000l).setParallelism(10).map(i->i+100).print("Sequence");

        //文件
        //FileProcessingMode.PROCESS_CONTINUOUSLY 监视文件种类为持续监视，如果有新内容会从头开始计算一次数据
        //FileProcessingMode.PROCESS_ONCE 只做一次操作然后退出
        DataStreamSource<String> dataStreamSource1 = environment.readFile(new TextInputFormat(null), "/Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile", FileProcessingMode.PROCESS_ONCE, 1000);
        dataStreamSource1.map(String::toUpperCase).print("file");

        environment.execute();
    }
}
