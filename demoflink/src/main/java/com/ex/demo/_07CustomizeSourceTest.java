package com.ex.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @Description 自定义resource 算子，可以参照SocketTextStreamFunction 这个例子他也是implements SourceFunction<String> 然后重写run(),cancel()两个方法
 * 1.实现 SourceFunction RichFunction 非并行算子
 * 2.实现 ParallelSourceFunction RichParallelSourceFunction 并行算子
 * 带Rich的会多了两个方法具体点进去看，还多了一个getRuntimeContext获取运行环境相关方法
 * @Author zhaolei
 * @Date 3/2/23 5:34 PM
 * @Version 1.0
 */
public class _07CustomizeSourceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //DataStreamSource<String> dataStreamSource = env.addSource(new mySourceFunction());//.setParallelism(2) 如果这么加会直接报错The parallelism of non parallel operator must be 1;
        //DataStreamSource<String> dataStreamSource = env.addSource(new myRichSourceFunction());//.setParallelism(2) 如果这么加会直接报错The parallelism of non parallel operator must be 1;
        //DataStreamSource<String> dataStreamSource = env.addSource(new myParallelSourceFunction()).setParallelism(5);//这个执行后可以看出来是并行执行的
        DataStreamSource<String> dataStreamSource = env.addSource(new myRichParallelSourceFunction()).setParallelism(5);//这个执行后可以看出来是并行执行的
        dataStreamSource.print();
        env.execute();
    }
}

class mySourceFunction implements SourceFunction<String> {
    private volatile boolean isRunning = true;//定义一个标记

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (isRunning) {
            ctx.collect(RandomStringUtils.randomAlphabetic(8));
            Thread.sleep(1000);//每次睡眠1秒然后继续发送数据给SourceContext
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}

class myParallelSourceFunction implements ParallelSourceFunction<String> {
    private volatile boolean isRunning = true;//定义一个标记

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (isRunning) {
            ctx.collect(RandomStringUtils.randomAlphabetic(8));
            Thread.sleep(1000);//每次睡眠1秒然后继续发送数据给SourceContext
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}


class myRichSourceFunction extends RichSourceFunction<String> {
    private volatile boolean isRunning = true;//定义一个标记

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (isRunning) {
            ctx.collect(RandomStringUtils.randomAlphabetic(8));
            Thread.sleep(1000);//每次睡眠1秒然后继续发送数据给SourceContext
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}

class myRichParallelSourceFunction extends RichParallelSourceFunction<String> {
    private volatile boolean isRunning = true;//定义一个标记

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (isRunning) {
            ctx.collect(RandomStringUtils.randomAlphabetic(8));
            Thread.sleep(1000);//每次睡眠1秒然后继续发送数据给SourceContext
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
