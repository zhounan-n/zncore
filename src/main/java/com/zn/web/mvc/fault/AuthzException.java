package com.zn.web.mvc.fault;

/**
 * Created by zhoun on 2018/7/17.
 **/
public class AuthzException extends RuntimeException {

    public AuthzException() {
        super();
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }
}
