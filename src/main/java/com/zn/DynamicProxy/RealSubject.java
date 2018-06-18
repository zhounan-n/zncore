package com.zn.DynamicProxy;

/**
 * Created by zhoun on 2018/6/18.
 **/
public class RealSubject implements Subject {

    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello:" + str);
    }
}
