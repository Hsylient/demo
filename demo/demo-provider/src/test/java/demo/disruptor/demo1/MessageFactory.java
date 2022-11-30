package demo.disruptor.demo1;
import com.lmax.disruptor.EventFactory;

// 定义消息工厂类
public class MessageFactory implements EventFactory<Message> {
    @Override
    public Message newInstance() {
        return new Message();
    }

}

