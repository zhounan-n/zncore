package com.zn.designpattern.behavior.templatemethod;

/**
 * 抽象模板角色类
 * Created by zhoun on 2018/6/21.
 **/
public abstract class AbstractTemplate {

    public void templateMethod(){
        //调用基本方法
        abstractMethod();
        hookMethod();
        concreateMethod();
    }

    /**
     * 基本方法的声明 由子类实现
     */
    protected abstract void abstractMethod();

    /**
     * 基本方法(空方法)
     */
    protected void hookMethod(){}

    private final void concreateMethod(){
        //业务相关的代码
    }

}
