package com.zn.designpattern.structrue.flyweight;

/**
 * 享元模式:对象的结构方式
 * 以共享的方式高效地支持大量的细粒度对象
 * java中的String就是享元模式，String对象是final类型，对象一旦创建就不可改变。在JAVA中字符串常量都是存在常量池中的
 *   String a = "abc"; String b = "abc"; a==b为true
 *   这样避免了创建N多相同对象的时候对资源的消耗
 *
 *   享元模式分为：单纯享元模式和复合享元模式
 *   在单纯的享元模式中，所有的享元对象都是可以共享的
 *
 * Created by zhoun on 2018/7/1.
 **/
public class Client {

    public static void main(String[] args) {

    }

}
