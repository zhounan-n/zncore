package com.zn.designpattern.structrue.proxy;

/**
 * 代理模式：对象的结构模式
 * 代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用
 * <p>
 * Created by zhoun on 2018/7/5.
 **/
public class Client {

    public static void main(String[] args) {
        AbstractObject object = new ProxyObject();
        object.operation();
    }


}
