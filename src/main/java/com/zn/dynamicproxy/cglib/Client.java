package com.zn.dynamicproxy.cglib;

/**
 * Created by zhoun on 2018/7/19.
 **/
public class Client {

    public static void main(String[] args) {
        Hello hello = CglibProxy.getInstance().getProxy(HelloImpl.class);
        hello.say("jack");
    }
}
