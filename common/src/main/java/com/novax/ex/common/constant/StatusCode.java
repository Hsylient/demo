package com.novax.ex.common.constant;

/**
 * Description: 请求响应状态码
 *
 * @author shaoqiping
 * @date 7/21/21 09:24
 */
public interface StatusCode {
    /** 请求成功 */
    Integer SUCCESS = 200;
    /** 请求失败 */
    Integer ERROR = 500;
    /** 服务异常 */
    Integer EXCEPTION = 510;
    /** 请求禁止 */
    Integer FORBIDDEN = 403;
    /** 未找到 */
    Integer NOT_FOUND = 404;
    /** 两步验证 */
    Integer TWO_STEP= 201;
    /** 需要滑块验证 */
    Integer BEHAVIOR_ERROR = 501;
    /** 弹框提示错误 */
    Integer ALERT_ERROR = 502;
    /** 提示错误并返回 */
    Integer RETURN_ERROR = 503;
    /** 国家地区不支持 */
    Integer NOT_ALLOWED = 504;
}
