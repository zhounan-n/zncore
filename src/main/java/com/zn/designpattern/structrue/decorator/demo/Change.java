package com.zn.designpattern.structrue.decorator.demo;

/**
 * 抽象装饰者角色
 * Created by zhoun on 2018/7/8.
 **/
public class Change implements TheGreatestSage {

    private TheGreatestSage theGreatestSage;

    public Change(TheGreatestSage theGreatestSage) {
        this.theGreatestSage = theGreatestSage;
    }

    @Override
    public void move() {
        //代码
        theGreatestSage.move();
    }
}
