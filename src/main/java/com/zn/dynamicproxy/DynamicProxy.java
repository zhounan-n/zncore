package com.zn.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * Created by zhoun on 2018/6/18.
 **/
public class DynamicProxy implements InvocationHandler {

    //定义需要代理的真实对象
    private Object subject;

    public DynamicProxy(Object subject) {
        //通过构造方法给代理对象赋值
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前可进行一些自己的操作
        System.out.println("before rent my house");
        System.out.println("Method:" + method);

        method.invoke(subject,args);

        System.out.println("after rent house");
        return null;
    }


}
