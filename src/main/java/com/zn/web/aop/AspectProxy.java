package com.zn.web.aop;

import com.zn.web.aop.proxy.Proxy;
import com.zn.web.aop.proxy.ProxyChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理
 * Created by zhoun on 2018/7/22.
 **/
public class AspectProxy implements Proxy {

    Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();

        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            } else {
                result = proxyChain.doProxyChain();
            }

        } catch (Exception e) {
            logger.error("aop异常", e);
            error(cls, method, params, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }


    public void begin() {

    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {

    }

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {

    }

    public void end() {

    }


}
