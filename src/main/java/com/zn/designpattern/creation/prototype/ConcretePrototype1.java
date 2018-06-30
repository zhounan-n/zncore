package com.zn.designpattern.creation.prototype;

/**
 * 具体原型角色
 * Created by zhoun on 2018/6/28.
 **/
public class ConcretePrototype1 implements Prototype {

    private String name;


    @Override
    public Prototype clone() {
        ConcretePrototype1 concretePrototype1 = new ConcretePrototype1();
        concretePrototype1.setName(name);
        return concretePrototype1;
    }

    @Override
    public String toString() {
        return "NOW IN prototype1 name=" + name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
