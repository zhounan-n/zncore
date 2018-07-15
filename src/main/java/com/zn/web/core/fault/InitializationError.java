package com.zn.web.core.fault;

/**
 * 初始化错误
 * Created by zhoun on 2018/7/15.
 **/
public class InitializationError extends Error {

    public InitializationError() {
        super();
    }

    public InitializationError(String message) {
        super(message);
    }

    public InitializationError(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationError(Throwable cause) {
        super(cause);
    }

}
