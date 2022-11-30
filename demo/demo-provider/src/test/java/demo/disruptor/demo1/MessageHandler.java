package demo.disruptor.demo1;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

// 定义消息处理器
public class MessageHandler implements EventHandler<Message>, WorkHandler<Message> {

    long start, end;

    private int handlerId;

    public MessageHandler(int handlerId) {
        this.handlerId = handlerId;
    }

    // 消费消息
    @Override
    public void onEvent(Message message, long sequence, boolean endOfBatch) throws Exception {
        handler(message);
        System.out.println("messageId：" + message.getId()
                + "\thandlerId：" + handlerId
                + "\tsequence：" + sequence
                + "\tendOfBatch：" + endOfBatch
                + "\t" + Thread.currentThread().getName());
    }

    @Override
    public void onEvent(Message message) throws Exception {
        handler(message);
        System.out.println("messageId：" + message.getId()
                + "\thandlerId：" + handlerId
                + "\t" + Thread.currentThread().getName());
    }

    public void handler(Message message) {
//        handler();
//        System.out.println("messageId：" + message.getId()
//                + "\thandlerId：" + handlerId
//                + "\t" + Thread.currentThread().getName());
    }

    private void handler() {
//        System.out.println("start = " + start);
//        System.out.println("end = " + end);
    }

}
