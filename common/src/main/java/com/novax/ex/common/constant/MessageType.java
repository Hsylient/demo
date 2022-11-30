package com.novax.ex.common.constant;


/**
 * 消息类型的定义
 * @author yusong
 * @date 20210707
 */
public interface MessageType {
	
//	String MAKER = "maker";//机器人行情相关
	String NO_LOSE_STORAGE = "noLoseStorage";//kafka 消息不可丢
	String LOSE_STORAGE = "loseStorage";//kafka消息可丢
	String SOCKET_LOSE_STORAGE = "socketLoseStorage";//kafka需要socket全部转发的可丢消息
}
