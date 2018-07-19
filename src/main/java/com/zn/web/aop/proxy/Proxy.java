package com.zn.web.aop.proxy;

/**
 * 代理接口
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
