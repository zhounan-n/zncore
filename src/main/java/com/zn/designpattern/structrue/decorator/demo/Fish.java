package com.zn.designpattern.structrue.decorator.demo;

/**
 * 具体装饰者角色 鱼儿
 * Created by zhoun on 2018/7/8.
 **/
public class Fish extends Change {

    public Fish(TheGreatestSage theGreatestSage) {
        super(theGreatestSage);
    }

    @Override
    public void move() {
        //代码
        System.out.println("fish move");

    }

}
