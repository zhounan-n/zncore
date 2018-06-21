package com.zn.designpattern.behavior.templatemethod;

/**
 * 具体模板角色类
 * 模板模式的关键是子类可以替换掉父类的可变部分，但不能替换父类的顶级逻辑
 * Created by zhoun on 2018/6/21.
 **/
public class ConcreteTemplate extends AbstractTemplate{

    /**
     * 基本方法的实现
     */
    @Override
    protected void abstractMethod() {
        //业务相关的代码
    }

    /**
     * 重写父类的方法
     */
    @Override
    protected void hookMethod() {
        //业务相关的代码
        super.hookMethod();
    }

}
