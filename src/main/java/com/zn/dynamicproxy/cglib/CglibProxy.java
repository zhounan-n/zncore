package com.zn.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/** cglib动态代理 (代理模式为静态代理)
 * Created by zhoun on 2018/7/18.
 **/
public class CglibProxy implements MethodInterceptor {

    private static CglibProxy cglibProxy = new CglibProxy();

    public static CglibProxy getInstance() {
        return cglibProxy;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        //方法级别的拦截
        Object result = methodProxy.invoke(o, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("before……");
    }

    private void after() {
        System.out.println("after……");
    }

    public static void main(String[] args) {
        /* CglibProxy cglibProxy = new CglibProxy();*/
        /* Hello hello = cglibProxy.getProxy(HelloImpl.class);*/
        Hello hello = getInstance().getProxy(HelloImpl.class);
        hello.say("jack");
    }
}
