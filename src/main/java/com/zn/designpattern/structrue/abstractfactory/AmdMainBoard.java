package com.zn.designpattern.structrue.abstractfactory;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class AmdMainBoard implements MainBoard {

    /**
     * CPU插槽的孔数
     */
    private int cpuHoles;

    /**
     * 构造方法，传入CPU插槽的孔数
     *
     */
    public AmdMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
    }

}
