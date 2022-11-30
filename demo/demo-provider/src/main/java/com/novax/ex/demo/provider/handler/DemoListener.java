package com.novax.ex.demo.provider.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description: RabbitMq队列demo
 *
 * @author my.miao
 * @date 2022/6/28 18:38
 */
@Slf4j
@Component
public class DemoListener {

    @Resource
    private AmqpTemplate rabbitTemplate;

    final public static String QUEUE_NAME = "cs_name";// 队列名
    final public static String TOPIC_KEY = "cs_key";// key
    final public static String EXCHANGE = "cs_yi_xia";// 交换机名称

    /**
     * 推送
     * @param json
     */
    public void csSend(String json) {
        log.info("send------1.EXCHANGE:{}-----TOPIC_KEY:{}-------meg:{}",EXCHANGE,TOPIC_KEY,json);
        rabbitTemplate.convertAndSend(EXCHANGE, TOPIC_KEY, json);// 发送到交换机，广播模式（key可以为""）
//        rabbitTemplate.convertAndSend(QUEUE_NAME,"laiya");// 单队列
//        rabbitTemplate.convertAndSend(QUEUE_NAME+"4","laiya");// 单队列
//        rabbitTemplate.convertAndSend(QUEUE_NAME+"5000","laiya");// 单队列
    }

    /**
     * 正常队列
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue(value = QUEUE_NAME, durable = "true"))// 队列创建与接收
    public void queueNamely(String msg) {
        log.info("cs----1.name:{}-----msg:{}",QUEUE_NAME,msg);
    }

    /**
     * 四个消费者队列
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue(value = QUEUE_NAME+"4", durable = "true"),concurrency = "4")// 队列创建与接收
    public void queueNamely4(String msg) {
        log.info("cs----1.name:{}-----msg:{}",QUEUE_NAME+"4",msg);
    }

    /**
     * 5秒消息过期
     * @param msg
     */
    @RabbitListener(queuesToDeclare = @Queue(value = QUEUE_NAME+"5000", durable = "true",
            arguments = @Argument(name = "x-message-ttl", value = "5000",type = "java.lang.Integer")))// 队列创建与接收
    public void queueNamely5(String msg) {
        log.info("cs----1.name:{}-----msg:{}",QUEUE_NAME+"5000",msg);
    }

    //------------------------------------------//
    /**
     * 交换机
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_NAME+"_e", durable = "true"),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC),
            key = TOPIC_KEY)
    )
    public void queueExchangeName(String msg) {
        log.info("1.name:{}-----msg:{}",QUEUE_NAME+"_e",msg);
    }

    /**
     * 交换机--四个消费者队列
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_NAME+"_e4", durable = "true"),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC),
            key = TOPIC_KEY),concurrency = "4"
    )
    public void queueExchangeName4(String msg) {
        log.info("1.name:{}-----msg:{}",QUEUE_NAME+"_e4",msg);
    }

    /**
     * 交换机--5秒消息过期
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_NAME+"_e5000", durable = "true",
                    arguments = @Argument(name = "x-message-ttl", value = "5000",type = "java.lang.Integer")),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC),
            key = TOPIC_KEY)
    )
    public void queueExchangeName5(String msg) {
        log.info("1.name:{}-----msg:{}",QUEUE_NAME+"_e5000",msg);
    }
}
