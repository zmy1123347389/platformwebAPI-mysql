package com.behere.common.utils.exam;

/**
 * 常量池
 *
 * @author nanbo
 */
public enum Constants {
    /**
     * 成功
     */
    SUCCESS("0", "成功"),
    /**
     * 失败
     */
    FAILURE("1", "失败"),

    /**
     * 用户名或密码不正确
     */
    MESSAGE_1010("1010", "用户名或密码不正确"),

    /**
     * 已答过此题
     */
    ANSWER("1", "您已经答过题"),
    /**
     * 签到成功
     */
    SIGNIN_SUCCESS("0", "签到成功"),
    /**
     * 签到失败
     */
    SIGNIN_FAILURE("1", "您今天已经签过到了，请明天再来！");

    private String code;

    private String desc;

    Constants(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(String code) {
        for (Constants con : Constants.values()) {
            if (con.getCode().equals(code)) {
                return con.getDesc();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}