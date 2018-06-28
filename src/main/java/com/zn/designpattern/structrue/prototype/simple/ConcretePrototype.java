package com.zn.designpattern.structrue.prototype.simple;

/**
 * 具体原型角色
 * Created by zhoun on 2018/6/28.
 **/
public class ConcretePrototype implements Prototype {

    @Override
    public Object clone() {
        //最简单的克隆 新建一个自身对象
        Prototype prototype = new ConcretePrototype();
        return prototype;
    }

}
