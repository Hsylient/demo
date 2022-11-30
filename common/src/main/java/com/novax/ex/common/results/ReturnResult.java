package com.novax.ex.common.results;

import com.novax.ex.common.constant.StatusCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ReturnResult<T> {

    private int code;
    private String msg;
    private T data;

    public ReturnResult() {
        this(CurrencyMessage.SUCCESS);
    }

    public ReturnResult(T data) {
        this(CurrencyMessage.SUCCESS, data);
    }

    public ReturnResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnResult(Enum e) {
        try {
            this.code = (Integer) e.getClass().getMethod("getCode").invoke(e);
            this.msg = (String) e.getClass().getMethod("getMsg").invoke(e);
        } catch (Exception e1) {
            log.info("ReturnResult(Enum e)反射异常:" + e1.getMessage());
        }
    }

    public ReturnResult(Enum e, T data) {
        try {
            this.code = (Integer) e.getClass().getMethod("getCode").invoke(e);
            this.msg = (String) e.getClass().getMethod("getMsg").invoke(e);
            this.data = data;
        } catch (Exception e1) {
            log.info("ReturnResult(Enum e,T data)枚举反射异常:" + e1.getMessage());
        }
    }

    public static <T> ReturnResult<T> success() {
        return success(null);
    }

    public static <T> ReturnResult<T> success(T t) {
        return new ReturnResult<>(t);
    }

    public static <T> ReturnResult<T> success(String msg) {
        return new ReturnResult<>(StatusCode.SUCCESS, msg);
    }

    public static <T> ReturnResult<T> success(String msg, T t) {
        return new ReturnResult<>(StatusCode.SUCCESS, msg, t);
    }

    public static <T> ReturnResult<T> fail(String msg) {
        return new ReturnResult<>(StatusCode.ERROR, msg);
    }

    public static <T> ReturnResult<T> result(Integer code, String msg) {
        return result(null, code, msg);
    }

    public static <T> ReturnResult<T> result(T data, Integer code, String msg) {
        return new ReturnResult<>(code, msg, data);
    }
    public boolean isSuccess() {
        return code == CurrencyMessage.SUCCESS.getCode();
    }
}
