package com.zn.dynamicproxy.cglib;

/**
 * Created by zhoun on 2018/7/19.
 **/
public class HelloImpl implements Hello {

    @Override
    public void say(String name) {
        System.out.println("hello" + name);
    }
}
