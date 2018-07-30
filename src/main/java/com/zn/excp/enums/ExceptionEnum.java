package com.zn.excp.enums;

/**
 * @author zhounan
 * created on 2018/7/30
 */
public enum ExceptionEnum {

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(-1, "未知错误"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND(-101, "用户不存在");


    private Integer statue;

    private String desc;

    ExceptionEnum(Integer status, String desc) {
        this.statue = status;
        this.desc = desc;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
