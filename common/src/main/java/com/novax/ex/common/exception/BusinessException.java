package com.novax.ex.common.exception;

/**
 * Description: 自定义异常
 *
 * @author shaw
 * @date 7/15/21 16:00
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -4495539371656587567L;

    public BusinessException(String message) {
        super(message);
    }
}
