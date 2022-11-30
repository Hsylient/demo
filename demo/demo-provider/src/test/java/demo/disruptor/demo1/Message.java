package demo.disruptor.demo1;

import lombok.Data;

// 定义事件类
@Data
public class Message {
    // 消息内容
    private int id;
}
