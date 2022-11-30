package com.novax.ex.common.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 消息体基础类
 * @author yusong
 * @date 20210707
 */
@Data
public class BaseMessage {
    /**
     * 消息的类型(消息是否可丢 消息是否需要顺序消费等类型的定义。可参考MessageType定义的消息类型)
     */
	private String messageType;
    /**
     * 发送方(消息发送方服务的名称)
     */
	private String caller;
    /**
     * 消息体 消息中携带的信息 到业务系统中解析
     */
	private String body;
    /**
     * 消息名称 消息所在系统的全路径(类路径信息，后续消息子系统根据该消息分发接收到的消息)
     */
	private String msgName;

    public BaseMessage() {
        this.msgName = this.getClass().getName();
    }

    public BaseMessage(String messageType, String caller, String body) {
        this();
        this.messageType = messageType;
        this.caller = caller;
        this.body = body;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

}
