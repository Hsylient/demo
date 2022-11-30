package demo.disruptor.demo1;

import com.lmax.disruptor.RingBuffer;

// 定义生产者
public class Producer {

    private final RingBuffer<Message> ringBuffer;

    public Producer(RingBuffer<Message> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(int data){
        long sequence = ringBuffer.next();
        try {
            Message message = ringBuffer.get(sequence);
            message.setId(data);
//            System.out.println(data);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}
