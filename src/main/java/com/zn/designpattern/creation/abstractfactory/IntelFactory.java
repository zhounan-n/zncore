package com.zn.designpattern.creation.abstractfactory;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class IntelFactory implements AbstractFactory{

    @Override
    public Cpu createCpu() {
        return new IntelCpu(755);
    }

    @Override
    public MainBoard createMainBoard() {
        return new IntelMainBoard(755);
    }


}
