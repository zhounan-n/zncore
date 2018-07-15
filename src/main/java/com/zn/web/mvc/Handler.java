package com.zn.web.mvc;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * 封装Action 方法相关信息
 * Created by zhoun on 2018/7/15.
 **/
public class Handler {

    private Class<?> actionClass;
    private Method actionMethod;
    private Matcher requestPathMatcher;

    public Handler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClass() {
        return actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public Matcher getRequestPathMatcher() {
        return requestPathMatcher;
    }

    public void setRequestPathMatcher(Matcher matcher) {
        this.requestPathMatcher = matcher;
    }

}
