package com.zn.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by zhoun on 2018/6/18.
 **/
public class Client {

    public static void main(String[] args) {
        //要代理的真实对象
        Subject realSubject = new RealSubject();
        //代理哪个真实对象就将这个对象传进去
        InvocationHandler handler = new DynamicProxy(realSubject);
        /**
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         */
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
        System.out.println(subject.getClass().getName());

        subject.rent();

        subject.hello("world");
    }

}

