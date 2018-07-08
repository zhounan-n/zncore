package com.zn.designpattern.structrue.adapter;

/**
 * 适配器角色Adapter扩展了Adaptee,同时又实现了目标(Target)接口。由于Adaptee没有提供sampleOperation2()方法，
 * 而目标接口又要求这个方法，因此适配器角色Adapter实现了这个方法。
 *
 * 类的适配器模式
 * Created by zhoun on 2018/7/8.
 **/
public class Adaptor extends Adaptee implements Target{

    @Override
    public void sampleOperation2() {
        //相关代码
    }

}
