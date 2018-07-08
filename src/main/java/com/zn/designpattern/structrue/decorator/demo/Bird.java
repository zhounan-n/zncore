package com.zn.designpattern.structrue.decorator.demo;

/**
 * 装饰者角色 bird
 * Created by zhoun on 2018/7/8.
 **/
public class Bird extends Change {


    public Bird(TheGreatestSage theGreatestSage) {
        super(theGreatestSage);
    }

    @Override
    public void move() {
        //代码
        System.out.println("bird move");
    }


}
