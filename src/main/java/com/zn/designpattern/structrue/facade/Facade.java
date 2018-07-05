package com.zn.designpattern.structrue.facade;

/**
 * 多个子系统的聚合，实现了客户端与子系统之间的解耦
 * 门面模式还可以选择性的暴露方法，供子系统内部使用的方法
 * Created by zhoun on 2018/7/5.
 **/
public class Facade {

    public void test(){
        MduleA mduleA=new MduleA();
        mduleA.testA();

        ModuleB moduleB=new ModuleB();
        moduleB.testB();

        ModuleC moduleC=new ModuleC();
        moduleC.testC();
    }
}
