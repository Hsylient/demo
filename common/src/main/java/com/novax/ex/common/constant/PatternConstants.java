package com.novax.ex.common.constant;

/**
 * Description: 正则表达式 -- 需整理
 *
 * @author Deucalion@novax.email
 * @date 2022/9/4 16:50
 */
public interface PatternConstants {

    /**
     * 不用验证
     */
    String NEED_NOT = "-";

    /**
     * 文本框一般限制长度
     */
    String TEXT_BOX = "-";

    /**
     * 姓名
     */
    String NAME = "[\\u4e00-\\u9fa5]{2,64}|[A-Za-z0-9]{2,64}";

    /**
     * 银行卡卡号
     */
    String BANK_NUMBER = "[0-9]{13,19}";

    /**
     * 手机号
     */
    String PHONE_NUMBER = "\\d{6,11}";

    /**
     * 国内手机号
     */
    String CN_PHONE_NUMBER = "1\\d{10}";

    /**
     * 邮箱
     */
    String EMAIL = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /**
     * 昵称
     */
    String NICKNAME = "[\\u4e00-\\u9fa5]{2,8}|[A-Za-z0-9]{2,10}";

    /**
     * 密码
     */
//    String PASSWORD = "(?=.*[0-9].*)(?=.*[A-Z].*)[0-9A-Za-z]{8,}";
    String PASSWORD = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d!\"#$%&'()*+,-./:;<=>?@^_{}~|\\[\\]\\\\`]{8,}$";

    /**
     * 身份证
     */
    String ID_CARD_NUM = "^[1-9]\\d{5}(19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * 密码
     */
    String BROKER_PASSWORD = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}";

    /**
     * 0以上的整数
     */
    String INTEGERTYPE = "^([0-9]\\d*)$";

    /**
     * 逗号隔开的大于0的数字组成，形如：10,20
     * 正则：^([1-9]\d+,)*[1-9]\d+$或^[1-9]\d+(,[1-9]\d+)*$
     */
//    String NUMBERS_REGEX = "^([1-9]\\d+,)*[1-9]\\d+$";
    String NUMBERS_REGEX = "^(\\d+,)*\\d+$";

    /**
     * 大于等于0的正数
     * "^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])$"//与下边的一样不包括0，正在使用中的包括0
     */
    String POSITIVE = "^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$";
    String NUMBERS = "^-?([1-9]\\d*(\\.[0-9]*[1-9])?)|-?(0\\.[0-9]*[1-9])|0$";

    //纯字母
    String ALPHA = "^[A-Za-z]+$";
    //字母+数字
    String ALPHA_NUM = "^[A-Za-z0-9]+$";
    //字母+数字+下划线+中划线
    String ALPHA_DASH = "^[A-Za-z0-9\\-\\_]+$";
    /** 用户名格式：2-15位字母数字下划线组成 */
    String USERNAME_INPUT_FORMAT = "^[A-Za-z0-9\\-\\_]{2,15}$";
    //汉字+字母+数字+下划线+中划线
    String CHS_ALPHA = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$";
    //汉字+字母+数字+下划线+中划线
    String CHS_DASH = "^[\\u4E00-\\u9FA5\\w]+$";

}


