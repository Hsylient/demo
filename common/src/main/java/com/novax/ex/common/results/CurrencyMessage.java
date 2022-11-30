package com.novax.ex.common.results;


/**
 * 通用的消息返回类型
 * @author yusong
 * @date 2021/06/16
 */
public enum CurrencyMessage {
	
	SUCCESS(200,"success"),
	FAIL(201,"fail"),
	FORBIDDEN(403,"Token过期"),
	REPEAT(413,"异地登出"),
	NOT_FOUND(404,"资源未找到"),
	PARAM_NOT_NULL(4001,"必传参数不能为空"),
	INTERNATIONAL_NOT_FOUND(4002,"国际化资源未找到"),
	SYSTEM_EXCEPTION(500,"系统异常"),
	TIME_OUT(504,"time out"),
	UNDEFINE(5001, "未定义异常信息"),
    RESUBMIT(5002, "重复提交"),
	PARAM_ERR(600,"参数错误");
	
	private int code;
	private String msg;
	CurrencyMessage(int code, String msg){
		this.code = code;
        this.msg = msg;
	}
	public static String msg(int code) {
        for (CurrencyMessage m : CurrencyMessage.values()) {
            if (m.getCode() == code) {
                return m.getMsg();
            }
        }
        return UNDEFINE.getMsg();
    }
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
