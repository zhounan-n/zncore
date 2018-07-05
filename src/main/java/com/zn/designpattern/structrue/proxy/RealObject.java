package com.zn.designpattern.structrue.proxy;

/**
 * 目标对象角色
 * Created by zhoun on 2018/7/5.
 **/
public class RealObject extends AbstractObject {

    @Override
    public void operation() {
        System.out.println("一些操作");
    }

}
