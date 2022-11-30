package demo.disruptor.demo1;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DisruptorTest {

    public static void main(String[] args) throws InterruptedException {
//        int num = 10_000_000;
        int num = 1;

        // 创建消息工厂
        EventFactory<Message> eventFactory = new MessageFactory();
        int ringBufferSize = 1024 * 1024 * 2;
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 创建disruptor
        Disruptor<Message> disruptor = new Disruptor<>(
                eventFactory, // 消息(event)工厂对象
                ringBufferSize, // 容器的长度
                threadFactory, // 线程池(建议使用自定义线程池) RejectedExecutionHandler
                ProducerType.SINGLE, // 单生产者 还是 多生产者
                new YieldingWaitStrategy() //等待策略
        );

        // 消息处理器
        MessageHandler h1 = new MessageHandler(1);
        MessageHandler h2 = new MessageHandler(2);
        MessageHandler h3 = new MessageHandler(3);
        MessageHandler h4 = new MessageHandler(4);
        MessageHandler h5 = new MessageHandler(5);
        MessageHandler h6 = new MessageHandler(6);
        MessageHandler h7 = new MessageHandler(7);
        // 以六边形的流程执行
        disruptor.handleEventsWith(h1, h2);
        // 1 要在 3 之前处理消息
        disruptor.after(h1).handleEventsWith(h3);
        disruptor.after(h2).handleEventsWith(h4); // 2在4之前
        disruptor.after(h3, h4).handleEventsWith(h5); // 5在3和4之前
        // 每个消息将仅由其中一个工作处理程序处理
        disruptor.handleEventsWithWorkerPool(h6, h7).then(h1); // 6和7 在 1 之前
        // 启动disruptor
        disruptor.start();

        // 获取实际存储数据的容器: RingBuffer
        RingBuffer<Message> ringBuffer = disruptor.getRingBuffer();
        // 生产
        Producer producer = new Producer(ringBuffer);
        long s = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            producer.onData(i);
        }
        System.out.println("Disruptor 事件入队总耗时：" + (System.currentTimeMillis() - s) + " ms");
        Thread.sleep(1000);
        disruptor.shutdown();
        System.out.println("Disruptor 执行完毕任务总耗时：" + (System.currentTimeMillis() - s - 1000) + " ms");
    }

}
