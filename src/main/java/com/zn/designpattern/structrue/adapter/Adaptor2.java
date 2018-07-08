package com.zn.designpattern.structrue.adapter;

/**
 * 对象的适配器模式
 * Created by zhoun on 2018/7/8.
 **/
public class Adaptor2 {

    private Adaptee adaptee;

    public Adaptor2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void sampleOperation1() {
        this.adaptee.sampleOperation1();
    }

    public void sampleOperation2() {
        //相关代码
    }


}
