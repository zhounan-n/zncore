package com.zn.designpattern.creation.prototype;

/**
 * 抽象原型角色
 */
public interface Prototype {
    Prototype clone();

    String getName();

    void setName(String name);
}
