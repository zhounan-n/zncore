package com.zn.designpattern.structrue.facade;

/**
 * Created by zhoun on 2018/7/5.
 **/
public class MduleA {

    /**
     * 示意方法
     */
    public void testA() {
        System.out.println("调用modulea中的test方法");
    }

    private void m1() {
        System.out.println("只给内部使用");
    }

}
