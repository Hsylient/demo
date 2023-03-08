package com.ex.demo;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description kafka 资源连接器
 * @Author zhaolei
 * @Date 3/1/23 8:19 PM
 * @Version 1.0
 */
public class _06KafkaSourceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Map<TopicPartition, Long> offsets = new HashMap<>();
        KafkaSource<String> source = KafkaSource.<String>builder()
                //设置kafka服务器地址
                .setBootstrapServers("localhost:9092")
                // 设置订阅的目标主题
                .setTopics("test")
                // 设置消费者组id
                .setGroupId("my-group")

                //设置读数据的偏移量
                //第一种
                .setStartingOffsets(OffsetsInitializer.earliest())//此模式设置为从最初数据开始读取
                //OffsetsInitializer.earliest() //可以查看源码中他继续设置偏移量为偏移量重置策略中使用的是OffsetResetStrategy.EARLIEST 这种策略
                //第二种
                //.setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST))//此模式可以将消费起始位移设置为之前所提交的偏移量（如果没有，则重置为LATEST）
                //.setStartingOffsets(OffsetsInitializer.latest())//设置为从最新的位置开始读
                //第三种
                //设置初始化的偏移量是记录时间戳大于或等于给定时间戳(毫秒)的第一条记录的偏移量。
                //OffsetsInitializer.timestamp(1866666666666l), //按照时间点位置进行读取
                //.setStartingOffsets(OffsetsInitializer.timestamp(1866666666666l))
                //第四种
                //偏移量设置按照指定分区，每个分区设置一个起使位置
                //.setStartingOffsets(OffsetsInitializer.offsets(offsets))

                //设置value数据的反序列化器
                .setValueOnlyDeserializer(new SimpleStringSchema())

                // 开启kafka底层消费者的自动位移提交机制
                // 它会把最新的消费位移提交到kafka的consumer_offsets中
                // 就算把自动位移提交机制开启，KafkaSource依然不依赖自动位移提交机制
                //（宕机重启时，优先从flink自己的状态中去获取偏移量<更可靠>）
                //.setProperty("auto.offset.commit", "true")

                // 把本source算子设置成  BOUNDED属性（有界流）
                // 将来本source去读取数据的时候，读到指定的位置，就停止读取并退出
                // 常用于补数或者重跑某一段历史数据
                // .setBounded(OffsetsInitializer.committedOffsets())

                // 把本source算子设置成  UNBOUNDED属性（无界流）
                // 但是并不会一直读数据，而是达到指定位置就停止读取，但程序不退出
                // 主要应用场景：需要从kafka中读取某一段固定长度的数据，然后拿着这段数据去跟另外一个真正的无界流联合处理
                //.setUnbounded(OffsetsInitializer.latest())

                .build();

        DataStreamSource<String> dataStreamSource = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");
        SingleOutputStreamOperator<String> upperCase = dataStreamSource.map(String::toUpperCase);
        upperCase.print();
        env.execute();
    }
}
