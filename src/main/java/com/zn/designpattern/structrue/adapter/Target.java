package com.zn.designpattern.structrue.adapter;

public interface Target {

    /**
     * 这是源类adapter也有的方法
     */
    void sampleOperation1();

    /**
     * 这是源类adapter没有的方法
     */
    void sampleOperation2();
}
