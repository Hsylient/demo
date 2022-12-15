package demo.disruptor.demo2;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @author zhenghao
 * @date 2022/8/10 11:41
 */
@Slf4j
public class Main {

    @Test
    public void test() {
        int ringBufferSize = 1024 * 1024; // ringBuffer 大小，必须是 2 的 N 次方；
//        int ringBufferSize = 4; // ringBuffer 大小，必须是 2 的 N 次方；
        Disruptor<LongEvent> disruptor = new Disruptor<>(
                LongEvent::new,
                ringBufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy());
        // 消费消息
//        disruptor.handleEventsWith((event, sequence, endOfBatch) ->
//                System.out.println("e: " + event + ", seq：" + sequence + ", eob：" + endOfBatch));
//        disruptor.handleEventsWithWorkerPool(System.out::println);
        disruptor.handleEventsWithWorkerPool(
                        longEvent -> {
                            log.info("longEvent1 = {}", longEvent);
                            sleep(3);
                        },
                        longEvent -> {
                            log.info("longEvent2 = {}", longEvent);
                            sleep(4);
                        })
                .then((event, sequence, endOfBatch) -> log.info("longEvent3 = {}, seq={}, eob={}", event, sequence, endOfBatch),
                        (event, sequence, endOfBatch) -> log.info("longEvent4 = {}, seq={}, eob={}", event, sequence, endOfBatch));
        disruptor.start();

        sleep(1);

        // 发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        long i = 999;
        for (int i = 100; i < 110; i++) {
            int finalI = i;
            Runnable task = () -> {
                long sequence = ringBuffer.next();//请求下一个事件序号；
                try {
                    LongEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
                    event.setValue(finalI);
                } finally {
                    ringBuffer.publish(sequence);//发布事件；
                }
            };
            new Thread(task).start();
        }
        sleep(3);
        disruptor.shutdown(); // 关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
    }

    private static void sleep(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Data
class LongEvent {
    private long value;
}
