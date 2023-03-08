package com.ex.demo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.List;

/**
 * @Description
 * @Author zhaolei
 * @Date 3/3/23 5:33 PM
 * @Version 1.0
 */
public class _08TransformationTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //获取json数据源
        DataStreamSource<String> streamSource = env.readTextFile("/Users/zhaolei/IdeaProjects/tenshi/demo/demoflink/src/testfile/json.txt");
        //先将文字字符串转换为user对象
        SingleOutputStreamOperator<User> map = streamSource.map(json -> JSON.parseObject(json, User.class));
        streamSource.print();
        env.execute();
    }
}
@Data
class User {
    private int id;

    private String name;

    private int age;

    private String gender;

    private List<Friends> friends;
}
@Data
class Friends {
    private int id;

    private String name;
}
