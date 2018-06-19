package com.zn.designpattern;

/**
 * 单例模式饿汉模式(时间换空间)
 * Created by zhoun on 2018/6/19.
 **/
public class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return singleton1;
    }


}
