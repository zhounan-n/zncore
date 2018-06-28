package com.zn.designpattern.structrue.AbstractFactory;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class IntelMainBoard implements MainBoard {


    /**
     * CPU插槽的孔数
     */
    private int cpuHoles;

    /**
     * 构造方法，传入CPU插槽的孔数
     *
     * @param cpuHoles
     */
    public IntelMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }


    @Override
    public void installCPU() {
        System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
    }

}
