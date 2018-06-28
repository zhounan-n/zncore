package com.zn.designpattern.structrue.abstractfactory;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class AmdFactory implements AbstractFactory{

    @Override
    public Cpu createCpu() {
        return new AmdCpu(755);
    }

    @Override
    public MainBoard createMainBoard() {
        return new AmdMainBoard(1112);
    }
}
