package com.zn.designpattern.creation.abstractfactory;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class AmdCpu implements Cpu {

    /**
     * CPU的针脚数
     */
    private int pins;

    public AmdCpu(int pins) {
        this.pins = pins;
    }

    @Override
    public void caculate() {
        System.out.println("AMD CPU的针脚数：" + pins);
    }


}
