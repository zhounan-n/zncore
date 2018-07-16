package com.zn.web.mvc.bean;

import java.sql.ResultSet;

/**
 * 封装返回数据
 * Created by zhoun on 2018/7/16.
 **/
public class Result {

    // 成功标志
    private boolean success;
    // 错误代码
    private int error;
    // 相关数据
    private Object data;

    public Result(boolean success) {
        this.success = success;
    }

    public Result error(int error) {
        this.error = error;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
