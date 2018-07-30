package com.zn.excp.entities;

/**
 * @author zhounan
 * created on 2018/7/30
 */

public class Result<T> {

    private Integer status;
    private String code;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
