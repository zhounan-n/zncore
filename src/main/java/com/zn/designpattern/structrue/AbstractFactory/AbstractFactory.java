package com.zn.designpattern.structrue.AbstractFactory;

public interface AbstractFactory {

    /**
     * 创建cpu对象
     */
    public Cpu createCpu();

    /**
     * 创建主板对象
     */
    public MainBoard createMainBoard();
}
