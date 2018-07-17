package com.zn.web.mvc.fault;

/**
 * Created by zhoun on 2018/7/17.
 **/
public class AuthcException extends RuntimeException {

    public AuthcException() {
        super();
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }
}
