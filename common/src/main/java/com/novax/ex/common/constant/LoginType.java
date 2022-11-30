package com.novax.ex.common.constant;

/**
 *
 * Description: 用户登录类型
 *
 * @author shaw
 * @date 6/28/22 2:33 PM
 */
public interface LoginType {

    /**
     * 前台用户 web登录
     */
    String WEB = "web";

    /**
     * 前台用户 app登录
     */
    String APP = "app";

    /**
     * 管理端
     */
    String ADMIN = "admin";

    /**
     * 代理商
     */
    String BROKER = "broker";

    /**
     * 脱敏后台
     */
    String TOURIST = "tourist";
}
