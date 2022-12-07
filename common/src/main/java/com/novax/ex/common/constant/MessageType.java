package com.novax.ex.common.constant;


/**
 * 消息类型的定义
 * @author yusong
 * @date 20210707
 */
public interface MessageType {
	
	String SOCKET_SPOT = "socketSpot";//kafka需要socket全部转发的可丢消息 -- 现货消息
	String SOCKET_SWAP = "socketSwap";//kafka需要socket全部转发的可丢消息 -- U合约消息
	String SOCKET_CRYPTO = "socketCrypto";//kafka需要socket全部转发的可丢消息 -- 币合约消息
}
