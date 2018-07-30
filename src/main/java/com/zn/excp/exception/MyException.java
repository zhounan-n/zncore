package com.zn.excp.exception;

import com.zn.excp.enums.ExceptionEnum;

/**
 * @author zhounan
 * created on 2018/7/30
 */
public class MyException extends RuntimeException {

    private Integer code;

    public MyException(String message) {
        super(message);
    }

    public MyException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getDesc());
        this.code = exceptionEnum.getStatue();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
