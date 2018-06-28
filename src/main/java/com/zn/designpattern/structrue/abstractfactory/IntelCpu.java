package com.zn.designpattern.structrue.abstractfactory;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class IntelCpu implements Cpu {

    private int pines = 0;

    public IntelCpu(int pines) {
        this.pines = pines;
    }

    @Override
    public void caculate() {
        System.out.println("intel cpu的针脚数：" + pines);
    }
}
